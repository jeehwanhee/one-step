package com.jeepark.onestep.ui.viewmodels

import com.jeepark.onestep.MainDispatcherRule
import com.jeepark.onestep.data.model.DAILY_QUEST_LIMIT
import com.jeepark.onestep.data.model.GiveUpReason
import com.jeepark.onestep.data.model.IsolatedRecord
import com.jeepark.onestep.data.model.Mood
import com.jeepark.onestep.data.model.Quest
import com.jeepark.onestep.data.model.QuestDate
import com.jeepark.onestep.data.model.User
import com.jeepark.onestep.data.model.needsAssessment
import com.jeepark.onestep.data.repository.FakeActiveQuestStore
import com.jeepark.onestep.data.repository.FakeAuthRepository
import com.jeepark.onestep.data.repository.FakeQuestRepository
import com.jeepark.onestep.data.repository.FakeSettingsRepository
import com.jeepark.onestep.data.repository.FakeUserRepository
import kotlinx.coroutines.CompletableDeferred
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

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

    // 서울 기준 2026-09-28 10:00:05
    private val fixedClock: Clock = Clock.fixed(Instant.parse("2026-09-28T01:00:05Z"), ZoneId.of("Asia/Seoul"))

    private inner class Fixture(
        user: User?,
        failUserLoad: Boolean = false,
        activeQuest: Quest? = null,
        uid: String? = "uid-1",
        writeGate: CompletableDeferred<Unit>? = null,
    ) {
        val userRepo = FakeUserRepository(user = user, shouldFail = failUserLoad).also { it.writeGate = writeGate }
        val questRepo = FakeQuestRepository()
        val store = FakeActiveQuestStore(activeQuest)
        val auth = FakeAuthRepository(uid = uid)
        val settings = FakeSettingsRepository(lastAccessMillis = 0L, lastCheckInMark = 7)
        val viewModel = MainViewModel(
            repo = userRepo,
            questRepository = questRepo,
            activeQuestStore = store,
            authRepository = auth,
            settingsRepository = settings,
            clock = fixedClock,
        )
    }

    private fun todayDate(): String = QuestDate.todayKey(fixedClock)

    // ===== 초기화 / 사용자 로딩 =====

    @Test
    fun `초기화하면 사용자를 불러오고 저장된 진행 중 퀘스트를 복원한다`() {
        val user = User(uid = "uid-1", nickname = "테스터", lastAccessDate = 0L)
        val f = Fixture(user, activeQuest = quest)

        assertEquals(user, f.viewModel.user.value)
        assertEquals(quest, f.viewModel.activeQuest.value)
        assertNull(f.viewModel.loadError.value)
        assertEquals(fixedClock.millis(), f.userRepo.user!!.lastAccessDate) // 서버의 접속일 갱신
    }

    @Test
    fun `사용자를 불러오면 이 기기의 접속 기록을 갱신하고 안부 알림 단계를 처음으로 돌린다`() {
        val f = Fixture(User(uid = "uid-1"))

        assertEquals(fixedClock.millis(), f.settings.lastAccessMillis)
        assertEquals(0, f.settings.lastCheckInMark) // 픽스처에서 7로 시작
    }

    // ===== 퀘스트 완료 =====

    @Test
    fun `퀘스트 완료에 성공하면 사용자 상태가 갱신되고 티어업 콜백이 호출된다`() {
        val f = Fixture(User(tier = 0, progress = 0, questsSinceAssessment = 2))
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
        assertEquals(3, updated.questsSinceAssessment)
        assertEquals(updated.questsSinceAssessment, f.userRepo.user!!.questsSinceAssessment) // 저장소와 화면 상태가 일치
        assertEquals(updated.prevQuests, f.userRepo.user!!.prevQuests)
        assertFalse(f.viewModel.isSavingQuest.value)
        assertTrue(tierUp)
        assertTrue(success)
        assertNull(error)
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
    fun `어제 한도에 도달했어도 날짜가 바뀌면 한도가 풀리고 오늘 카운트는 1부터 시작한다`() {
        val f = Fixture(User(dailyQuestDate = "2026-09-27", dailyQuestCount = DAILY_QUEST_LIMIT))
        f.questRepo.quests = listOf(quest)

        assertFalse(f.viewModel.isDailyLimitReached())
        f.viewModel.loadFilteredQuests(mood = Mood.NEUTRAL, onReady = {}, onError = {})

        assertEquals(true, f.questRepo.lastUseGemini)
        assertEquals(1, f.viewModel.user.value!!.dailyQuestCount)
        assertEquals("2026-09-28", f.viewModel.user.value!!.dailyQuestDate)
    }

    // ===== 재설문 =====

    private val surveyed = listOf(IsolatedRecord(score = 50, recordedAt = 1L))

    private fun completeOnce(f: Fixture) = f.viewModel.saveCompletedQuest(
        quest = quest, answer = "답", onTierUp = {}, onSuccess = {}, onError = {},
    )

    @Test
    fun `설문 후 아홉 번째 완료까지는 재설문이 필요 없고 열 번째 완료에서 필요해진다`() {
        val f = Fixture(User(isolatedHistory = surveyed, questsSinceAssessment = 8))

        completeOnce(f)
        assertFalse(needsAssessment(f.viewModel.user.value!!)) // 9번째

        completeOnce(f)
        assertTrue(needsAssessment(f.viewModel.user.value!!)) // 10번째
        assertTrue(needsAssessment(f.userRepo.user!!)) // 저장소 쪽 상태도 같은 판단
    }

    // ===== 서버 확인(Firestore 쓰기 응답)을 기다리는 것과 기다리지 않는 것 =====
    // 게이트를 열지 않으면 서버가 응답하지 않는 상황(오프라인·지연)이다. Firestore 쓰기는 서버가 확인해야 끝난다.

    @Test
    fun `서버 확인이 오지 않아도 사용자 로드는 끝나고 이 기기의 접속 기록은 갱신된다`() {
        val gate = CompletableDeferred<Unit>()
        val f = Fixture(User(uid = "uid-1", lastAccessDate = 0L), writeGate = gate)

        assertNotNull(f.viewModel.user.value)
        assertNull(f.viewModel.loadError.value)
        assertEquals(fixedClock.millis(), f.settings.lastAccessMillis) // 기기 기록은 바로
        assertEquals(0L, f.userRepo.user!!.lastAccessDate) // 서버 기록은 아직

        gate.complete(Unit)

        assertEquals(fixedClock.millis(), f.userRepo.user!!.lastAccessDate)
    }

    @Test
    fun `일일 카운트의 서버 확인이 늦어도 퀘스트 목록은 바로 표시되고 확인이 오면 카운트가 오른다`() {
        val gate = CompletableDeferred<Unit>()
        val f = Fixture(User(), writeGate = gate)
        f.questRepo.quests = listOf(quest)
        var ready: List<Quest>? = null

        f.viewModel.loadFilteredQuests(mood = Mood.NEUTRAL, onReady = { ready = it }, onError = {})

        assertEquals(listOf(quest), ready)
        assertFalse(f.viewModel.isLoadingQuests.value)
        assertEquals(0, f.viewModel.user.value!!.dailyQuestCount) // 서버 확인 전에는 반영하지 않는다

        gate.complete(Unit)

        assertEquals(1, f.viewModel.user.value!!.dailyQuestCount)
    }

    @Test
    fun `완료 저장은 서버 확인이 올 때까지 저장 중으로 기다리고 확인이 오면 반영한다`() {
        val gate = CompletableDeferred<Unit>()
        val f = Fixture(User(tier = 0), writeGate = gate)
        var success = false

        f.viewModel.saveCompletedQuest(
            quest = quest, answer = "답", onTierUp = {}, onSuccess = { success = true }, onError = {},
        )

        assertTrue(f.viewModel.isSavingQuest.value)
        assertFalse(success)
        assertTrue(f.viewModel.user.value!!.prevQuests.isEmpty())

        gate.complete(Unit)

        assertFalse(f.viewModel.isSavingQuest.value)
        assertTrue(success)
        assertEquals(1, f.viewModel.user.value!!.prevQuests.size)
    }
}
