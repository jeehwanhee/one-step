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
    private val questRepository = com.jeepark.onestep.util.QuestRepository()
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
            onSuccess = { _user.value = it },
            onFailure = {}
        )
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

                val quests = questRepository.fetchFilteredQuests(ratios)
                _questList.value = quests
                onReady(quests)
            } catch (e: Exception) {
                android.util.Log.e("QuestLoad", "퀘스트 로드 실패: ${e.message}", e)
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
                "prevQuests"        to FieldValue.arrayUnion(prevQuestMap)
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
                prevQuests        = currentUser.prevQuests + newPrevQuest
            )
            if (didTierUp) onTierUp()
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

    fun saveGiveUpQuest(quest: Quest) {
        val currentUser = _user.value ?: return
        val uid = auth.currentUser?.uid ?: return

        val newResultsQueue = (currentUser.questResultsQueue + 0).takeLast(10)

        db.collection("users").document(uid).update(
            mapOf("questResultsQueue" to newResultsQueue)
        ).addOnSuccessListener {
            _user.value = currentUser.copy(questResultsQueue = newResultsQueue)
        }
    }
}
