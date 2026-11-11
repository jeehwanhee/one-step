package com.jeepark.onestep.ui.screens.collection

import com.jeepark.onestep.MainDispatcherRule
import com.jeepark.onestep.data.repository.FakeUserRepository
import com.jeepark.onestep.domain.model.PrevQuest
import com.jeepark.onestep.domain.model.User
import com.jeepark.onestep.ui.animal.AnimalIds
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test

class CollectionViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `사용자 로드에 성공하면 user와 완료 퀘스트, 해금 동물이 채워진다`() {
        // Arrange: tier 2 -> 해금 티어 0,1,2인 병아리·거북이·고양이가 해금
        val earlier = PrevQuest(questName = "먼저 완료", doneDate = "2026.01.01 10:00:00")
        val later = PrevQuest(questName = "나중에 완료", doneDate = "2026.02.01 10:00:00")
        val user = User(nickname = "테스터", tier = 2, prevQuests = listOf(earlier, later))
        val fakeRepo = FakeUserRepository(user = user)

        // Act
        val viewModel = CollectionViewModel(fakeRepo)

        // Assert
        assertEquals(user, viewModel.user.value)
        assertEquals(listOf(later, earlier), viewModel.completedQuests.value) // 최신순 정렬
        assertEquals(
            listOf(AnimalIds.CHICK, AnimalIds.TURTLE, AnimalIds.CAT),
            viewModel.unlockedAnimals.value.map { it.id }
        )
        assertNull(viewModel.loadError.value)
    }

    @Test
    fun `메인 화면에서 퀘스트를 완료하면 다시 불러오지 않아도 완료 목록과 해금 동물이 바뀐다`() {
        // 하단 탭 이동은 ViewModel을 유지하므로, 한 번만 읽으면 앱을 다시 켤 때까지 갱신되지 않던 버그
        val fakeRepo = FakeUserRepository(user = User(tier = 0))
        val viewModel = CollectionViewModel(fakeRepo)
        val done = PrevQuest(questName = "시 한 편 읽기", doneDate = "2026.11.11 10:00:00")

        fakeRepo.user = User(tier = 1, prevQuests = listOf(done))

        assertEquals(listOf(done), viewModel.completedQuests.value)
        assertEquals(listOf(AnimalIds.CHICK, AnimalIds.TURTLE), viewModel.unlockedAnimals.value.map { it.id })
    }
}
