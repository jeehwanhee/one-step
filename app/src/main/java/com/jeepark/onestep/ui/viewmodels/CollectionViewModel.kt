package com.jeepark.onestep.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.jeepark.onestep.data.model.PrevQuest
import com.jeepark.onestep.data.model.User
import com.jeepark.onestep.util.FirestoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// 티어별 해금 동물: 0=병아리(0티어), 1=거북이(1), 2=고양이(2), 3=강아지(3), 4=파랑새(4), 5=곰(5), 6=말(6), 7=돌고래(7)
private val UNLOCK_TIER = listOf(0, 1, 2, 3, 4, 5, 6, 7)

class CollectionViewModel : ViewModel() {
    private val repo = FirestoreRepository()

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()

    private val _completedQuests = MutableStateFlow<List<PrevQuest>>(emptyList())
    val completedQuests: StateFlow<List<PrevQuest>> = _completedQuests.asStateFlow()

    private val _unlockedAnimals = MutableStateFlow<List<Int>>(emptyList())
    val unlockedAnimals: StateFlow<List<Int>> = _unlockedAnimals.asStateFlow()

    private val _loadError = MutableStateFlow<String?>(null)
    val loadError: StateFlow<String?> = _loadError.asStateFlow()

    init {
        loadUser()
    }

    fun loadUser() {
        _loadError.value = null
        repo.getUser(
            onSuccess = { user ->
                _user.value = user
                _completedQuests.value = user.prevQuests.sortedByDescending { it.doneDate }
                _unlockedAnimals.value = UNLOCK_TIER
                    .mapIndexedNotNull { idx, requiredTier ->
                        if (user.tier >= requiredTier) idx else null
                    }
            },
            onFailure = { e ->
                android.util.Log.e("CollectionVM", "사용자 정보 로드 실패", e)
                _loadError.value = e.message ?: "정보를 불러오지 못했어요"
            }
        )
    }
}
