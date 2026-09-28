package com.jeepark.onestep.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeepark.onestep.data.repository.ActiveQuestStore
import com.jeepark.onestep.data.repository.AuthRepository
import com.jeepark.onestep.data.repository.QuestRepository
import com.jeepark.onestep.data.repository.SettingsRepository
import com.jeepark.onestep.data.repository.UserRepository
import com.jeepark.onestep.domain.model.GiveUpEntryFields
import com.jeepark.onestep.domain.model.GiveUpReason
import com.jeepark.onestep.domain.model.Mood
import com.jeepark.onestep.domain.model.Quest
import com.jeepark.onestep.domain.model.User
import com.jeepark.onestep.domain.model.dailyQuota
import com.jeepark.onestep.domain.model.progression
import com.jeepark.onestep.domain.model.withDailyQuota
import com.jeepark.onestep.domain.rules.QuestDate
import com.jeepark.onestep.domain.rules.computeQuestCompletion
import com.jeepark.onestep.domain.rules.giveUpResultsQueue
import com.jeepark.onestep.domain.rules.hasReachedDailyLimit
import com.jeepark.onestep.domain.rules.incrementedDailyCount
import com.jeepark.onestep.oneStepApp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Clock

class MainViewModel(
    private val repo: UserRepository,
    private val questRepository: QuestRepository,
    private val activeQuestStore: ActiveQuestStore,
    private val authRepository: AuthRepository,
    private val settingsRepository: SettingsRepository,
    private val clock: Clock = Clock.systemDefaultZone(),
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
        viewModelScope.launch {
            repo.getUser().fold(
                onSuccess = { user ->
                    _user.value = user
                    val uid = authRepository.currentUid ?: return@fold
                    val now = clock.millis()
                    // 서버 확인을 기다리지 않는다: 오프라인이어도 화면과 기기 기록은 그대로 진행돼야 한다
                    launch {
                        repo.updateLastAccessDate(uid, now)
                            .onFailure { android.util.Log.w("MainViewModel", "접속일 갱신 실패", it) }
                    }
                    // 이 기기의 안부 알림 기준: 접속 시각을 갱신하고, 서버에 저장된 알림 동의 여부를 기기 설정에 맞춘다
                    settingsRepository.recordAccess(now)
                    settingsRepository.setNotificationsEnabled(user.notificationAgreed)
                },
                onFailure = { e ->
                    android.util.Log.e("MainViewModel", "사용자 정보 로드 실패", e)
                    _loadError.value = e.message ?: "정보를 불러오지 못했어요"
                }
            )
        }
    }

    fun isDailyLimitReached(): Boolean =
        _user.value?.let { hasReachedDailyLimit(it.dailyQuota(), QuestDate.todayKey(clock)) } ?: false

    private fun incrementDailyCount() {
        val currentUser = _user.value ?: return
        val uid = authRepository.currentUid ?: return
        val today = QuestDate.todayKey(clock)
        val sameDay = currentUser.dailyQuestDate == today

        // 같은 날이면 원자적 증가(race-free), 날짜가 바뀌었으면 1로 리셋.
        // 퀘스트 목록 표시가 서버 확인을 기다리지 않도록 별도 코루틴으로 띄운다.
        viewModelScope.launch {
            repo.incrementDailyQuestCount(uid, sameDay, today)
                .onSuccess {
                    _user.value = currentUser.withDailyQuota(incrementedDailyCount(currentUser.dailyQuota(), today))
                }
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

                val ratios = com.jeepark.onestep.domain.service.getQuestDifficulty(
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
        val uid = authRepository.currentUid ?: return

        _isSavingQuest.value = true

        val completion = computeQuestCompletion(
            currentUser.progression(), quest, answer, QuestDate.doneDateNow(clock),
        )

        viewModelScope.launch {
            repo.applyQuestCompletion(
                uid             = uid,
                progress        = completion.newProgress,
                tier            = completion.newTier,
                difficultyQueue = completion.newDifficultyQueue,
                resultsQueue    = completion.newResultsQueue,
                prevQuestMap    = completion.toPrevQuestMap(),
            ).fold(
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
    }

    fun startQuest(quest: Quest) {
        _activeQuest.value = quest
        activeQuestStore.save(quest)
    }

    fun clearActiveQuest() {
        _activeQuest.value = null
        activeQuestStore.clear()
    }

    fun saveGiveUpQuest(
        quest: Quest,
        reason: GiveUpReason,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val currentUser = _user.value ?: return
        val uid = authRepository.currentUid ?: return

        val newResultsQueue = giveUpResultsQueue(currentUser.progression())

        val giveUpEntry = mapOf(
            GiveUpEntryFields.QUEST_NAME to quest.questName,
            GiveUpEntryFields.REASON     to reason.code
        )

        // users 문서: 실패 이력 + 포기 사유 추가 (이게 성공해야 activeQuest를 지움)
        viewModelScope.launch {
            repo.applyGiveUp(
                uid          = uid,
                resultsQueue = newResultsQueue,
                giveUpEntry  = giveUpEntry,
            ).fold(
                onSuccess = {
                    _user.value = currentUser.copy(questResultsQueue = newResultsQueue)
                    onSuccess()
                },
                onFailure = { e ->
                    android.util.Log.e("MainViewModel", "saveGiveUpQuest user update failed", e)
                    onError("저장하지 못했어요. 다시 시도해주세요")
                }
            )
        }

        // quests 문서: 해당 퀘스트의 포기 사유 추가 (통계용, 실패해도 위 흐름과 무관)
        questRepository.recordGiveUp(quest.index, reason) { e ->
            android.util.Log.e("MainViewModel", "saveGiveUpQuest quest update failed", e)
        }
    }

    companion object {
        /** 앱 컨테이너의 실제 구현체를 연결한 ViewModel 팩토리. */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val container = oneStepApp().container
                MainViewModel(
                    repo               = container.userRepository,
                    questRepository    = container.questRepository,
                    activeQuestStore   = container.activeQuestStore,
                    authRepository     = container.authRepository,
                    settingsRepository = container.settingsRepository,
                )
            }
        }
    }
}
