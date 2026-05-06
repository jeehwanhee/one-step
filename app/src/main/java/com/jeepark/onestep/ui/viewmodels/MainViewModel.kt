package com.jeepark.onestep.ui.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import com.jeepark.onestep.data.model.EXPAMOUNT
import com.jeepark.onestep.data.model.PrevQuest
import com.jeepark.onestep.data.model.Quest
import com.jeepark.onestep.data.model.User
import com.jeepark.onestep.util.FirestoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = FirestoreRepository()
    private val questRepository = com.jeepark.onestep.util.QuestRepository(application)
    private val db = Firebase.firestore
    private val auth = Firebase.auth
    private val prefs = application.getSharedPreferences("active_quest", Context.MODE_PRIVATE)

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()

    private val _questList = MutableStateFlow<List<Quest>>(emptyList())
    val questList: StateFlow<List<Quest>> = _questList.asStateFlow()

    private val _isLoadingQuests = MutableStateFlow(false)
    val isLoadingQuests: StateFlow<Boolean> = _isLoadingQuests.asStateFlow()

    private val _activeQuest = MutableStateFlow<Quest?>(null)
    val activeQuest: StateFlow<Quest?> = _activeQuest.asStateFlow()

    init {
        loadUser()
        loadActiveQuestFromPrefs()
    }

    fun loadUser() {
        repo.getUser(
            onSuccess = { user ->
                _user.value = user
                // 접속일 갱신
                val uid = auth.currentUser?.uid ?: return@getUser
                val now = System.currentTimeMillis()
                db.collection("users").document(uid).update("lastAccessDate", now)
                com.jeepark.onestep.util.NotificationHelper.saveLastAccess(getApplication())
                // Firestore의 알림 동의 여부를 SharedPreferences에 동기화
                val prefs = getApplication<android.app.Application>()
                    .getSharedPreferences(com.jeepark.onestep.util.NotificationHelper.PREFS_NAME, android.content.Context.MODE_PRIVATE)
                prefs.edit().putBoolean(com.jeepark.onestep.util.NotificationHelper.KEY_NOTIF, user.notificationAgreed).apply()
            },
            onFailure = {}
        )
    }

    private fun todayDate() = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())

    fun isDailyLimitReached(): Boolean {
        val user = _user.value ?: return false
        return user.dailyQuestDate == todayDate() && user.dailyQuestCount >= 20
    }

    private fun incrementDailyCount() {
        val currentUser = _user.value ?: return
        val uid = auth.currentUser?.uid ?: return
        val today = todayDate()

        if (currentUser.dailyQuestDate == today) {
            // 같은 날 → 원자적 증가 (race-free)
            db.collection("users").document(uid).update(
                "dailyQuestCount", FieldValue.increment(1)
            ).addOnSuccessListener {
                _user.value = currentUser.copy(
                    dailyQuestCount = currentUser.dailyQuestCount + 1
                )
            }
        } else {
            // 날짜 바뀜 → 1로 리셋
            db.collection("users").document(uid).update(
                mapOf(
                    "dailyQuestCount" to 1,
                    "dailyQuestDate"  to today
                )
            ).addOnSuccessListener {
                _user.value = currentUser.copy(dailyQuestCount = 1, dailyQuestDate = today)
            }
        }
    }

    fun loadFilteredQuests(
        mood: Int,
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
        onDone: () -> Unit
    ) {
        val currentUser = _user.value ?: return
        val uid = auth.currentUser?.uid ?: return

        var newProgress = currentUser.progress + quest.questEXP
        var newTier = currentUser.tier

        while (newTier < EXPAMOUNT.size) {
            val threshold = EXPAMOUNT[newTier]
            if (newProgress >= threshold) {
                newProgress -= threshold
                newTier++
            } else break
        }

        val didTierUp = newTier > currentUser.tier

        val newDifficultyQueue = (currentUser.difficultyQueue + quest.difficulty.toDouble()).takeLast(10)
        val newResultsQueue    = (currentUser.questResultsQueue + 1).takeLast(10)

        val prevQuestMap = mapOf(
            "questName"       to quest.questName,
            "questEXP"        to quest.questEXP,
            "difficulty"      to quest.difficulty,
            "confirmQuestion" to quest.confirmQuestion,
            "confirmAnswer"   to answer,
            "doneDate"        to SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault()).format(Date())
        )

        db.collection("users").document(uid).update(
            mapOf(
                "progress"          to newProgress,
                "tier"              to newTier,
                "difficultyQueue"   to newDifficultyQueue,
                "questResultsQueue" to newResultsQueue,
                "prevQuests"        to FieldValue.arrayUnion(prevQuestMap),
                "isolatedCount"     to FieldValue.increment(1)
            )
        ).addOnSuccessListener {
            val newPrevQuest = PrevQuest(
                questName       = quest.questName,
                questEXP        = quest.questEXP,
                difficulty      = quest.difficulty,
                confirmQuestion = quest.confirmQuestion,
                confirmAnswer   = answer,
                doneDate        = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault()).format(Date())
            )
            _user.value = currentUser.copy(
                progress          = newProgress,
                tier              = newTier,
                difficultyQueue   = newDifficultyQueue,
                questResultsQueue = newResultsQueue,
                prevQuests        = currentUser.prevQuests + newPrevQuest,
                isolatedCount     = currentUser.isolatedCount + 1
            )
            if (didTierUp) onTierUp()
            onDone()
        }.addOnFailureListener { e ->
            android.util.Log.e("MainViewModel", "saveCompletedQuest failed", e)
            // 다이얼로그가 영원히 멈추지 않도록 onDone 호출
            onDone()
        }
    }

    fun startQuest(quest: Quest) {
        _activeQuest.value = quest
        prefs.edit()
            .putInt("index", quest.index)
            .putString("questName", quest.questName)
            .putInt("difficulty", quest.difficulty)
            .putString("confirmQuestion", quest.confirmQuestion)
            .putInt("questEXP", quest.questEXP)
            .apply()
    }

    fun clearActiveQuest() {
        _activeQuest.value = null
        prefs.edit().clear().apply()
    }

    private fun loadActiveQuestFromPrefs() {
        val name = prefs.getString("questName", null) ?: return
        _activeQuest.value = Quest(
            index           = prefs.getInt("index", 0),
            questName       = name,
            difficulty      = prefs.getInt("difficulty", 1),
            confirmQuestion = prefs.getString("confirmQuestion", "") ?: "",
            questEXP        = prefs.getInt("questEXP", 0)
        )
    }

    fun resetIsolatedCount() {
        val uid = auth.currentUser?.uid ?: return
        db.collection("users").document(uid).update("isolatedCount", 0)
            .addOnSuccessListener {
                _user.value = _user.value?.copy(isolatedCount = 0)
            }
    }

    fun saveGiveUpQuest(quest: Quest, reason: Int) {
        val currentUser = _user.value ?: return
        val uid = auth.currentUser?.uid ?: return

        val newResultsQueue = (currentUser.questResultsQueue + 0).takeLast(10)

        val giveUpEntry = mapOf(
            "questName" to quest.questName,
            "reason"    to reason
        )

        // users 문서: 실패 이력 + 포기 사유 추가
        db.collection("users").document(uid).update(
            mapOf(
                "questResultsQueue" to newResultsQueue,
                "giveUpReasons"     to FieldValue.arrayUnion(giveUpEntry)
            )
        ).addOnSuccessListener {
            _user.value = currentUser.copy(questResultsQueue = newResultsQueue)
        }.addOnFailureListener { e ->
            android.util.Log.e("MainViewModel", "saveGiveUpQuest user update failed", e)
        }

        // quests 문서: 해당 퀘스트의 포기 사유 추가
        db.collection("quests")
            .whereEqualTo("index", quest.index)
            .get()
            .addOnSuccessListener { snapshot ->
                snapshot.documents.firstOrNull()?.reference?.update(
                    "giveUpReasons", FieldValue.arrayUnion(reason)
                )?.addOnFailureListener { e ->
                    android.util.Log.e("MainViewModel", "saveGiveUpQuest quest update failed", e)
                }
            }.addOnFailureListener { e ->
                android.util.Log.e("MainViewModel", "saveGiveUpQuest quest query failed", e)
            }
    }
}
