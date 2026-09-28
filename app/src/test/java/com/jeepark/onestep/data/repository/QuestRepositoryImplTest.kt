package com.jeepark.onestep.data.repository

import com.jeepark.onestep.data.model.Coordinates
import com.jeepark.onestep.data.model.DIFFICULTY_LEVELS
import com.jeepark.onestep.data.model.Place
import com.jeepark.onestep.data.model.Quest
import com.jeepark.onestep.data.model.SAMPLE_SIZE
import com.jeepark.onestep.data.model.SELECTION_SIZE
import com.jeepark.onestep.util.FakeLocationProvider
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test
import kotlin.random.Random

class QuestRepositoryImplTest {

    private val cityHall = Coordinates(37.5665, 126.9780)
    private val ratios = listOf(0.1, 0.2, 0.4, 0.2, 0.1)

    /** 난이도마다 [perLevel]개씩 이름이 모두 다른 퀘스트. */
    private fun quests(perLevel: Int = 10): List<Quest> =
        DIFFICULTY_LEVELS.flatMap { level ->
            (1..perLevel).map { n ->
                Quest(index = level * 100 + n, questName = "퀘스트 $level-$n", difficulty = level, questEXP = 10)
            }
        }

    private class Fixture(
        quests: List<Quest>,
        places: List<Place> = emptyList(),
        location: Coordinates? = null,
    ) {
        val questSource = FakeCollectionSource(serverVersion = 1L, items = quests)
        val placeRepository = FakePlaceRepository(places)
        val locationProvider = FakeLocationProvider(location)
        val weather = FakeWeatherProvider("기온 3.0°C, 비")
        val ranker = FakeQuestRanker()

        val repository = QuestRepositoryImpl(
            questsCache = VersionedCache(questSource, FakeVersionStore()),
            places = placeRepository,
            location = locationProvider,
            weather = weather,
            ranker = ranker,
            random = Random(1),
        )
    }

    // ===== 퀘스트 불러오기 =====

    @Test
    fun `퀘스트가 하나도 없으면 실패한다`() = runTest {
        val f = Fixture(quests = emptyList())

        val failure = runCatching { f.repository.fetchFilteredQuests(ratios, useGemini = true) }.exceptionOrNull()

        assertEquals("quests 컬렉션이 비어 있습니다", failure?.message)
    }

    @Test
    fun `이름이 비어 있는 퀘스트는 후보에서 뺀다`() = runTest {
        val named = quests(perLevel = 4)
        val unnamed = (1..30).map { Quest(index = 9000 + it, questName = "", difficulty = 3) }
        val f = Fixture(quests = named + unnamed)

        f.repository.fetchFilteredQuests(ratios, useGemini = true)

        assertTrue(f.ranker.receivedQuests!!.all { it.questName.isNotEmpty() })
    }

    @Test
    fun `이름 있는 퀘스트가 하나도 없으면 비어 있다고 본다`() = runTest {
        val f = Fixture(quests = listOf(Quest(index = 1, questName = "", difficulty = 1)))

        val failure = runCatching { f.repository.fetchFilteredQuests(ratios, useGemini = true) }.exceptionOrNull()

        assertNotNull(failure)
    }

    // ===== 추천 서비스를 쓰는 경우 =====

    @Test
    fun `난이도 비율대로 후보 20개를 뽑아 날씨와 함께 추천 서비스에 넘기고 그 결과를 돌려준다`() = runTest {
        val f = Fixture(quests = quests())
        val chosen = quests().take(SELECTION_SIZE)
        f.ranker.result = { chosen }

        val result = f.repository.fetchFilteredQuests(ratios, useGemini = true)

        assertEquals(chosen, result)
        assertEquals(SAMPLE_SIZE, f.ranker.receivedQuests!!.size)
        assertEquals(
            listOf(2, 4, 8, 4, 2),
            DIFFICULTY_LEVELS.map { level -> f.ranker.receivedQuests!!.count { it.difficulty == level } },
        )
        assertEquals("기온 3.0°C, 비", f.ranker.receivedWeather)
    }

    @Test
    fun `추천 서비스가 실패하면 무작위 8개로 대신한다`() = runTest {
        val f = Fixture(quests = quests())
        f.ranker.error = java.io.IOException("Gemini 오류")

        val result = f.repository.fetchFilteredQuests(ratios, useGemini = true)

        assertEquals(SELECTION_SIZE, result.size)
        assertTrue(f.ranker.receivedQuests!!.containsAll(result))
    }

    @Test
    fun `코루틴 취소는 무작위로 대신하지 않고 그대로 전파한다`() = runTest {
        val f = Fixture(quests = quests())
        f.ranker.error = CancellationException("취소됨")

        val failure = runCatching { f.repository.fetchFilteredQuests(ratios, useGemini = true) }.exceptionOrNull()

        assertTrue(failure is CancellationException)
    }

    // ===== 일일 한도를 넘은 경우 =====

    @Test
    fun `추천을 쓰지 않으면 날씨도 추천 서비스도 부르지 않고 무작위 8개를 돌려준다`() = runTest {
        val f = Fixture(quests = quests())

        val result = f.repository.fetchFilteredQuests(ratios, useGemini = false)

        assertEquals(SELECTION_SIZE, result.size)
        assertEquals(0, f.ranker.callCount)
        assertEquals(0, f.weather.callCount)
    }

    // ===== 자리표시자 치환 =====

    @Test
    fun `자리표시자는 가까운 장소 이름으로 바뀌어서 추천 서비스에 넘어간다`() = runTest {
        val park = Place(type = "park", name = "서울숲", lat = cityHall.lat + 0.005, lng = cityHall.lng)
        val list = listOf(Quest(index = 1, questName = "{공원}에서 산책하기", difficulty = 3, confirmQuestion = "{공원}은 어땠나요?"))
        val f = Fixture(quests = list, places = listOf(park), location = cityHall)

        f.repository.fetchFilteredQuests(ratios, useGemini = true)

        val sent = f.ranker.receivedQuests!!.single()
        assertEquals("서울숲에서 산책하기", sent.questName)
        assertEquals("서울숲은 어땠나요?", sent.confirmQuestion)
    }

    @Test
    fun `위치를 모르면 대체 이름으로 바뀐다`() = runTest {
        val list = listOf(Quest(index = 1, questName = "{도서관}에 가기", difficulty = 3))
        val f = Fixture(quests = list, places = listOf(Place(type = "library", name = "구립도서관")), location = null)

        f.repository.fetchFilteredQuests(ratios, useGemini = true)

        assertEquals("근처 도서관에 가기", f.ranker.receivedQuests!!.single().questName)
    }

    @Test
    fun `추천을 쓰지 않는 경우에도 자리표시자는 바뀐다`() = runTest {
        val park = Place(type = "park", name = "서울숲", lat = cityHall.lat + 0.005, lng = cityHall.lng)
        val list = listOf(Quest(index = 1, questName = "{공원} 걷기", difficulty = 3))
        val f = Fixture(quests = list, places = listOf(park), location = cityHall)

        val result = f.repository.fetchFilteredQuests(ratios, useGemini = false)

        assertEquals("서울숲 걷기", result.single().questName)
    }

    @Test
    fun `장소 데이터를 불러오지 못하면 치환하지 않고 원래 문구로 계속한다`() = runTest {
        val list = listOf(Quest(index = 1, questName = "{공원}에서 산책하기", difficulty = 3))
        val f = Fixture(quests = list, location = cityHall)
        f.placeRepository.error = java.io.IOException("장소 조회 실패")

        val result = f.repository.fetchFilteredQuests(ratios, useGemini = false)

        assertEquals("{공원}에서 산책하기", result.single().questName)
    }

    @Test
    fun `자리표시자가 하나도 없으면 장소 데이터를 불러오지 않는다`() = runTest {
        val f = Fixture(quests = quests())

        f.repository.fetchFilteredQuests(ratios, useGemini = true)

        assertEquals(0, f.placeRepository.loadCount)
    }

    @Test
    fun `장소 조회 중 코루틴 취소는 그대로 전파한다`() = runTest {
        val list = listOf(Quest(index = 1, questName = "{공원} 걷기", difficulty = 3))
        val f = Fixture(quests = list, location = cityHall)
        f.placeRepository.error = CancellationException("취소됨")

        try {
            f.repository.fetchFilteredQuests(ratios, useGemini = false)
            fail("취소가 전파되어야 한다")
        } catch (e: CancellationException) {
            assertEquals("취소됨", e.message)
        }
    }
}
