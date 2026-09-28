package com.jeepark.onestep.ui.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.jeepark.onestep.data.model.GiveUpReason
import com.jeepark.onestep.data.model.Mood
import com.jeepark.onestep.data.model.Quest
import com.jeepark.onestep.data.model.User
import com.jeepark.onestep.data.model.computeQuestCompletion
import com.jeepark.onestep.data.model.giveUpResultsQueue
import com.jeepark.onestep.data.model.hasReachedDailyLimit
import com.jeepark.onestep.data.model.incrementedDailyCount
import com.jeepark.onestep.data.repository.ActiveQuestStore
import com.jeepark.onestep.data.repository.QuestRepository
import com.jeepark.onestep.data.repository.QuestRepositoryImpl
import com.jeepark.onestep.data.repository.SharedPrefsActiveQuestStore
import com.jeepark.onestep.data.repository.UserRepository
import com.jeepark.onestep.data.repository.UserRepositoryImpl
import com.jeepark.onestep.util.NotificationHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainViewModel(
    private val repo: UserRepository,
    private val questRepository: QuestRepository,
    private val activeQuestStore: ActiveQuestStore,
    private val currentUid: () -> String?,
    private val onUserLoaded: (User) -> Unit = {},
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()

    private val _questList = MutableStateFlow<List<Quest>>(emptyList())
    val questList: StateFlow<List<Quest>> = _questList.asStateFlow()

    private val _isLoadingQuests = MutableStateFlow(false)
    val isLoadingQuests: StateFlow<Boolean> = _isLoadingQuests.asStateFlow()

    private val _activeQuest = MutableStateFlow<Quest?>(null)
    val activeQuest: StateFlow<Quest?> = _activeQuest.asStateFlow()

    private val _isSavingQuest = MutableStateFlow(false)
    val isSavingQuest: StateFlow<Boolean> = _isSavingQuest.asStateFlow()

    private val _loadError = MutableStateFlow<String?>(null)
    val loadError: StateFlow<String?> = _loadError.asStateFlow()

    init {
        loadUser()
        _activeQuest.value = activeQuestStore.load()
    }

    fun loadUser() {
        _loadError.value = null
        repo.getUser(
            onSuccess = { user ->
                _user.value = user
                // 접속일 갱신
                val uid = currentUid() ?: return@getUser
                repo.updateLastAccessDate(uid, System.currentTimeMillis())
                onUserLoaded(user)
            },
            onFailure = { e ->
                android.util.Log.e("MainViewModel", "사용자 정보 로드 실패", e)
                _loadError.value = e.message ?: "정보를 불러오지 못했어요"
            }
        )
    }

    private fun todayDate() = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())

    fun isDailyLimitReached(): Boolean = hasReachedDailyLimit(_user.value, todayDate())

    private fun incrementDailyCount() {
        val currentUser = _user.value ?: return
        val uid = currentUid() ?: return
        val today = todayDate()
        val sameDay = currentUser.dailyQuestDate == today

        // 같은 날이면 원자적 증가(race-free), 날짜가 바뀌었으면 1로 리셋
        repo.incrementDailyQuestCount(uid, sameDay, today) {
            _user.value = incrementedDailyCount(currentUser, today)
        }
    }

    fun loadFilteredQuests(
        mood: Mood,
        onReady: (List<Quest>) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            _isLoadingQuests.value = true
            try {
                val u = _user.value
                val prevSuccess = u?.questResultsQueue?.sum() ?: 0
                val prevDiff = u?.difficultyQueue?.takeIf { it.isNotEmpty() }?.average() ?: 3.0

                val ratios = com.jeepark.onestep.util.getQuestDifficulty(
                    isolation   = u?.isolated?.toDouble() ?: 50.0,
                    mood        = mood,
                    prevSuccess = prevSuccess,
                    prevDiff    = prevDiff,
                    tier        = u?.tier ?: 0
                )

                val useGemini = !isDailyLimitReached()
                val quests = questRepository.fetchFilteredQuests(ratios, useGemini)
                _questList.value = quests
                if (useGemini) incrementDailyCount()  // Gemini 호출한 경우만 카운트
                onReady(quests)
            } catch (e: Exception) {
                onError(e.message ?: "퀘스트를 불러오지 못했어요")
            } finally {
                _isLoadingQuests.value = false
            }
        }
    }

    fun saveCompletedQuest(
        quest: Quest,
        answer: String,
        onTierUp: () -> Unit,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val currentUser = _user.value ?: return
        val uid = currentUid() ?: return

        _isSavingQuest.value = true

        val doneDate   = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault()).format(Date())
        val completion = computeQuestCompletion(currentUser, quest, answer, doneDate)

        repo.applyQuestCompletion(
            uid             = uid,
            progress        = completion.newProgress,
            tier            = completion.newTier,
            difficultyQueue = completion.newDifficultyQueue,
            resultsQueue    = completion.newResultsQueue,
            prevQuestMap    = completion.toPrevQuestMap(),
            onSuccess = {
                _user.value = completion.applyTo(currentUser)
                _isSavingQuest.value = false
                if (completion.didTierUp) onTierUp()
                onSuccess()
            },
            onFailure = { e ->
                android.util.Log.e("MainViewModel", "saveCompletedQuest failed", e)
                _isSavingQuest.value = false
                // activeQuest를 지우지 않아 재시도 가능하게 유지
                onError("저장하지 못했어요. 다시 시도해주세요")
            }
        )
    }

    fun startQuest(quest: Quest) {
        _activeQuest.value = quest
        activeQuestStore.save(quest)
    }

    fun clearActiveQuest() {
        _activeQuest.value = null
        activeQuestStore.clear()
    }

    fun resetIsolatedCount() {
        val uid = currentUid() ?: return
        repo.resetIsolatedCount(uid) {
            _user.value = _user.value?.copy(isolatedCount = 0)
        }
    }

    fun saveGiveUpQuest(
        quest: Quest,
        reason: GiveUpReason,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val currentUser = _user.value ?: return
        val uid = currentUid() ?: return

        val newResultsQueue = giveUpResultsQueue(currentUser)

        val giveUpEntry = mapOf(
            "questName" to quest.questName,
            "reason"    to reason.code
        )

        // users 문서: 실패 이력 + 포기 사유 추가 (이게 성공해야 activeQuest를 지움)
        repo.applyGiveUp(
            uid          = uid,
            resultsQueue = newResultsQueue,
            giveUpEntry  = giveUpEntry,
            onSuccess = {
                _user.value = currentUser.copy(questResultsQueue = newResultsQueue)
                onSuccess()
            },
            onFailure = { e ->
                android.util.Log.e("MainViewModel", "saveGiveUpQuest user update failed", e)
                onError("저장하지 못했어요. 다시 시도해주세요")
            }
        )

        // quests 문서: 해당 퀘스트의 포기 사유 추가 (통계용, 실패해도 위 흐름과 무관)
        questRepository.recordGiveUp(quest.index, reason) { e ->
            android.util.Log.e("MainViewModel", "saveGiveUpQuest quest update failed", e)
        }
    }

    companion object {
        /** 실제 구현체(Firestore/SharedPreferences/FirebaseAuth)를 연결한 ViewModel 팩토리. */
        fun factory(app: Application): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MainViewModel(
                    repo             = UserRepositoryImpl(),
                    questRepository  = QuestRepositoryImpl(app),
                    activeQuestStore = SharedPrefsActiveQuestStore(app),
                    currentUid       = { Firebase.auth.currentUser?.uid },
                    onUserLoaded     = { user -> syncNotificationSettings(app, user) },
                )
            }
        }

        // 사용자 로딩 후: 마지막 접속 시각 저장 + Firestore의 알림 동의 여부를 SharedPreferences에 동기화
        private fun syncNotificationSettings(app: Application, user: User) {
            NotificationHelper.saveLastAccess(app)
            app.getSharedPreferences(NotificationHelper.PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(NotificationHelper.KEY_NOTIF, user.notificationAgreed)
                .apply()
        }
    }
}
