package com.jeepark.onestep.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.jeepark.onestep.data.model.Animal
import com.jeepark.onestep.data.model.AnimalRegistry
import com.jeepark.onestep.data.model.PrevQuest
import com.jeepark.onestep.data.model.User
import com.jeepark.onestep.data.repository.UserRepository
import com.jeepark.onestep.data.repository.UserRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CollectionViewModel(
    private val repo: UserRepository = UserRepositoryImpl()
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()

    private val _completedQuests = MutableStateFlow<List<PrevQuest>>(emptyList())
    val completedQuests: StateFlow<List<PrevQuest>> = _completedQuests.asStateFlow()

    private val _unlockedAnimals = MutableStateFlow<List<Animal>>(emptyList())
    val unlockedAnimals: StateFlow<List<Animal>> = _unlockedAnimals.asStateFlow()

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
                _unlockedAnimals.value = AnimalRegistry.unlockedAt(user.tier)
            },
            onFailure = { e ->
                android.util.Log.e("CollectionVM", "사용자 정보 로드 실패", e)
                _loadError.value = e.message ?: "정보를 불러오지 못했어요"
            }
        )
    }
}
