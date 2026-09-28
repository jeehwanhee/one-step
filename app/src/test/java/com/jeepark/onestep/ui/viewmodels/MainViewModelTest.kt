package com.jeepark.onestep.ui.viewmodels

import com.jeepark.onestep.MainDispatcherRule
import com.jeepark.onestep.data.model.DAILY_QUEST_LIMIT
import com.jeepark.onestep.data.model.GiveUpReason
import com.jeepark.onestep.data.model.Mood
import com.jeepark.onestep.data.model.Quest
import com.jeepark.onestep.data.model.User
import com.jeepark.onestep.data.repository.FakeActiveQuestStore
import com.jeepark.onestep.data.repository.FakeQuestRepository
import com.jeepark.onestep.data.repository.FakeUserRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val quest = Quest(
        index = 7,
        questName = "공원 벤치에 앉기",
        difficulty = 3,
        confirmQuestion = "무슨 냄새가 났나요?",
        questEXP = 15,
    )

    private class Fixture(
        user: User?,
        failUserLoad: Boolean = false,
        activeQuest: Quest? = null,
        uid: String? = "uid-1",
    ) {
        val userRepo = FakeUserRepository(user = user, shouldFail = failUserLoad)
        val questRepo = FakeQuestRepository()
        val store = FakeActiveQuestStore(activeQuest)
        val loadedUsers = mutableListOf<User>()
        val viewModel = MainViewModel(
            repo = userRepo,
            questRepository = questRepo,
            activeQuestStore = store,
            currentUid = { uid },
            onUserLoaded = { loadedUsers.add(it) },
        )
    }

    private fun todayDate(): String =
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

    // ===== 초기화 / 사용자 로딩 =====

    @Test
    fun `초기화하면 사용자를 불러오고 저장된 진행 중 퀘스트를 복원한다`() {
        val user = User(uid = "uid-1", nickname = "테스터", lastAccessDate = 0L)
        val f = Fixture(user, activeQuest = quest)

        assertEquals(user, f.viewModel.user.value)
        assertEquals(quest, f.viewModel.activeQuest.value)
        assertNull(f.viewModel.loadError.value)
        assertEquals(listOf(user), f.loadedUsers)
        assertTrue(f.userRepo.user!!.lastAccessDate > 0L) // 접속일 갱신
    }

    @Test
    fun `사용자 로드에 실패하면 loadError가 채워지고 onUserLoaded는 호출되지 않는다`() {
        val f = Fixture(User(), failUserLoad = true)

        assertNull(f.viewModel.user.value)
        assertNotNull(f.viewModel.loadError.value)
        assertTrue(f.loadedUsers.isEmpty())
    }

    @Test
    fun `로그인 uid가 없으면 사용자는 채워지지만 접속일 갱신과 onUserLoaded는 건너뛴다`() {
        val user = User(nickname = "테스터", lastAccessDate = 0L)
        val f = Fixture(user, uid = null)

        assertEquals(user, f.viewModel.user.value)
        assertTrue(f.loadedUsers.isEmpty())
        assertEquals(0L, f.userRepo.user!!.lastAccessDate)
    }

    // ===== 퀘스트 완료 =====

    @Test
    fun `퀘스트 완료에 성공하면 사용자 상태가 갱신되고 티어업 콜백이 호출된다`() {
        val f = Fixture(User(tier = 0, progress = 0, isolatedCount = 2))
        var tierUp = false
        var success = false
        var error: String? = null

        f.viewModel.saveCompletedQuest(
            quest = quest,
            answer = "풀 냄새",
            onTierUp = { tierUp = true },
            onSuccess = { success = true },
            onError = { error = it },
        )

        val updated = f.viewModel.user.value!!
        assertEquals(1, updated.tier) // EXP 15 = tier 0 문턱
        assertEquals(1, updated.prevQuests.size)
        assertEquals("풀 냄새", updated.prevQuests[0].confirmAnswer)
        assertEquals(3, updated.isolatedCount)
        assertFalse(f.viewModel.isSavingQuest.value)
        assertTrue(tierUp)
        assertTrue(success)
        assertNull(error)
    }

    @Test
    fun `티어가 오르지 않는 완료에서는 티어업 콜백을 호출하지 않는다`() {
        val f = Fixture(User(tier = 0, progress = 0))
        var tierUp = false

        f.viewModel.saveCompletedQuest(
            quest = quest.copy(questEXP = 5),
            answer = "답",
            onTierUp = { tierUp = true },
            onSuccess = {},
            onError = {},
        )

        assertFalse(tierUp)
        assertEquals(0, f.viewModel.user.value!!.tier)
        assertEquals(5, f.viewModel.user.value!!.progress)
    }

    @Test
    fun `퀘스트 완료 저장에 실패하면 onError만 호출되고 사용자와 진행 중 퀘스트는 유지된다`() {
        val user = User(tier = 0, progress = 0)
        val f = Fixture(user)
        f.viewModel.startQuest(quest)
        f.userRepo.shouldFail = true
        var success = false
        var error: String? = null

        f.viewModel.saveCompletedQuest(
            quest = quest,
            answer = "답",
            onTierUp = {},
            onSuccess = { success = true },
            onError = { error = it },
        )

        assertEquals("저장하지 못했어요. 다시 시도해주세요", error)
        assertFalse(success)
        assertFalse(f.viewModel.isSavingQuest.value)
        assertEquals(user, f.viewModel.user.value)
        assertEquals(quest, f.viewModel.activeQuest.value)
    }

    @Test
    fun `사용자가 로드되지 않았으면 완료 저장은 아무것도 하지 않는다`() {
        val f = Fixture(User(), failUserLoad = true)
        var called = false

        f.viewModel.saveCompletedQuest(
            quest = quest,
            answer = "답",
            onTierUp = { called = true },
            onSuccess = { called = true },
            onError = { called = true },
        )

        assertFalse(called)
        assertFalse(f.viewModel.isSavingQuest.value)
    }

    // ===== 퀘스트 포기 =====

    @Test
    fun `퀘스트 포기에 성공하면 결과 큐에 실패가 추가되고 포기 사유가 기록된다`() {
        val f = Fixture(User(questResultsQueue = listOf(1, 1)))
        var success = false

        f.viewModel.saveGiveUpQuest(quest, reason = GiveUpReason.BAD_SITUATION, onSuccess = { success = true }, onError = {})

        assertEquals(listOf(1, 1, 0), f.viewModel.user.value!!.questResultsQueue)
        assertTrue(success)
        assertEquals(listOf(7 to GiveUpReason.BAD_SITUATION), f.questRepo.giveUpRecords)
    }

    @Test
    fun `퀘스트 포기 저장에 실패하면 onError가 호출되고 사용자는 그대로다`() {
        val user = User(questResultsQueue = listOf(1, 1))
        val f = Fixture(user)
        f.userRepo.shouldFail = true
        var success = false
        var error: String? = null

        f.viewModel.saveGiveUpQuest(quest, reason = GiveUpReason.BAD_SITUATION, onSuccess = { success = true }, onError = { error = it })

        assertEquals("저장하지 못했어요. 다시 시도해주세요", error)
        assertFalse(success)
        assertEquals(user, f.viewModel.user.value)
    }

    // ===== 진행 중 퀘스트 보관 =====

    @Test
    fun `startQuest는 진행 중 퀘스트를 저장소에 저장하고 clearActiveQuest는 지운다`() {
        val f = Fixture(User())

        f.viewModel.startQuest(quest)
        assertEquals(quest, f.viewModel.activeQuest.value)
        assertEquals(quest, f.store.stored)

        f.viewModel.clearActiveQuest()
        assertNull(f.viewModel.activeQuest.value)
        assertNull(f.store.stored)
    }

    // ===== 퀘스트 조회 =====

    @Test
    fun `퀘스트 조회에 성공하면 목록이 채워지고 onReady가 호출되며 일일 카운트가 오른다`() {
        val f = Fixture(User())
        val fetched = listOf(quest, quest.copy(index = 8))
        f.questRepo.quests = fetched
        var ready: List<Quest>? = null

        f.viewModel.loadFilteredQuests(mood = Mood.NEUTRAL, onReady = { ready = it }, onError = {})

        assertEquals(fetched, f.viewModel.questList.value)
        assertEquals(fetched, ready)
        assertFalse(f.viewModel.isLoadingQuests.value)
        assertEquals(true, f.questRepo.lastUseGemini)
        assertEquals(5, f.questRepo.lastRatios!!.size)
        assertEquals(1, f.viewModel.user.value!!.dailyQuestCount)
    }

    @Test
    fun `일일 한도에 도달했으면 Gemini를 쓰지 않고 카운트도 올리지 않는다`() {
        val f = Fixture(User(dailyQuestDate = todayDate(), dailyQuestCount = DAILY_QUEST_LIMIT))
        f.questRepo.quests = listOf(quest)
        var ready = false

        f.viewModel.loadFilteredQuests(mood = Mood.NEUTRAL, onReady = { ready = true }, onError = {})

        assertEquals(false, f.questRepo.lastUseGemini)
        assertEquals(DAILY_QUEST_LIMIT, f.viewModel.user.value!!.dailyQuestCount)
        assertTrue(ready)
    }

    @Test
    fun `퀘스트 조회에 실패하면 onError가 호출되고 로딩이 끝난다`() {
        val f = Fixture(User())
        f.questRepo.fetchError = Exception("네트워크 오류")
        var error: String? = null

        f.viewModel.loadFilteredQuests(mood = Mood.NEUTRAL, onReady = {}, onError = { error = it })

        assertEquals("네트워크 오류", error)
        assertFalse(f.viewModel.isLoadingQuests.value)
        assertTrue(f.viewModel.questList.value.isEmpty())
    }

    // ===== 기타 =====

    @Test
    fun `resetIsolatedCount는 사용자의 isolatedCount를 0으로 만든다`() {
        val f = Fixture(User(isolatedCount = 10))

        f.viewModel.resetIsolatedCount()

        assertEquals(0, f.viewModel.user.value!!.isolatedCount)
    }
}
