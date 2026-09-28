package com.jeepark.onestep.data.repository

import com.jeepark.onestep.data.model.Coordinates
import com.jeepark.onestep.data.model.DIFFICULTY_LEVELS
import com.jeepark.onestep.data.model.Place
import com.jeepark.onestep.data.model.Quest
import com.jeepark.onestep.data.model.SAMPLE_SIZE
import com.jeepark.onestep.data.model.SELECTION_SIZE
import com.jeepark.onestep.util.FakeLocationProvider
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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
}
