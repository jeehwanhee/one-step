package com.jeepark.onestep.ui.viewmodels

import com.jeepark.onestep.data.model.PrevQuest
import com.jeepark.onestep.data.model.User
import com.jeepark.onestep.data.repository.FakeUserRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class CollectionViewModelTest {

    @Test
    fun `사용자 로드에 성공하면 user와 완료 퀘스트, 해금 동물이 채워진다`() {
        // Arrange: tier 2 -> UNLOCK_TIER[0,1,2,3,4,5,6,7] 기준으로 0,1,2가 해금
        val earlier = PrevQuest(questName = "먼저 완료", doneDate = "2026.01.01 10:00:00")
        val later = PrevQuest(questName = "나중에 완료", doneDate = "2026.02.01 10:00:00")
        val user = User(nickname = "테스터", tier = 2, prevQuests = listOf(earlier, later))
        val fakeRepo = FakeUserRepository(user = user)

        // Act
        val viewModel = CollectionViewModel(fakeRepo)

        // Assert
        assertEquals(user, viewModel.user.value)
        assertEquals(listOf(later, earlier), viewModel.completedQuests.value) // 최신순 정렬
        assertEquals(listOf(0, 1, 2), viewModel.unlockedAnimals.value)
        assertNull(viewModel.loadError.value)
    }

    @Test
    fun `사용자 로드에 실패하면 loadError가 채워진다`() {
        // Arrange
        val fakeRepo = FakeUserRepository(shouldFail = true, failureMessage = "네트워크 오류")

        // Act
        val viewModel = CollectionViewModel(fakeRepo)

        // Assert
        assertNotNull(viewModel.loadError.value)
        assertEquals("네트워크 오류", viewModel.loadError.value)
    }

    @Test
    fun `실패 후 재시도가 성공하면 loadError가 지워진다`() {
        // Arrange
        val fakeRepo = FakeUserRepository(shouldFail = true)
        val viewModel = CollectionViewModel(fakeRepo)
        assertNotNull(viewModel.loadError.value)

        // Act: 조건을 고쳐두고 재시도
        fakeRepo.shouldFail = false
        fakeRepo.user = User(nickname = "재시도", tier = 0)
        viewModel.loadUser()

        // Assert
        assertNull(viewModel.loadError.value)
        assertEquals("재시도", viewModel.user.value?.nickname)
    }
}
