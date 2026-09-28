package com.jeepark.onestep.ui.screens.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeepark.onestep.data.repository.UserRepository
import com.jeepark.onestep.domain.model.PrevQuest
import com.jeepark.onestep.domain.model.User
import com.jeepark.onestep.oneStepApp
import com.jeepark.onestep.ui.animal.Animal
import com.jeepark.onestep.ui.animal.AnimalRegistry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CollectionViewModel(
    private val repo: UserRepository
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
        viewModelScope.launch {
            repo.getUser().fold(
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

    companion object {
        /** 앱 컨테이너의 실제 구현체를 연결한 ViewModel 팩토리. */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer { CollectionViewModel(oneStepApp().container.userRepository) }
        }
    }
}
