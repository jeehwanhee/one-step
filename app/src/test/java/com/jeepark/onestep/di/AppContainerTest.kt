package com.jeepark.onestep.di

import com.jeepark.onestep.data.repository.FakeActiveQuestStore
import com.jeepark.onestep.data.repository.FakeQuestRepository
import com.jeepark.onestep.data.repository.FakeUserRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class AppContainerTest {

    private class Counted<T>(private val create: () -> T) {
        var count = 0
            private set

        fun provider(): () -> T = { count++; create() }
    }

    private class Fixture {
        val users = Counted { FakeUserRepository() }
        val quests = Counted { FakeQuestRepository() }
        val store = Counted { FakeActiveQuestStore() }

        val container = AppContainer(
            createUserRepository = users.provider(),
            createQuestRepository = quests.provider(),
            createActiveQuestStore = store.provider(),
        )
    }

    @Test
    fun `컨테이너를 만들 때는 아무 저장소도 만들지 않는다`() {
        val f = Fixture()

        assertEquals(0, f.users.count)
        assertEquals(0, f.quests.count)
        assertEquals(0, f.store.count)
    }

    @Test
    fun `저장소는 처음 꺼낼 때 한 번만 만들어지고 이후에는 같은 인스턴스를 돌려준다`() {
        val f = Fixture()

        val first = f.container.userRepository
        val second = f.container.userRepository

        assertSame(first, second)
        assertEquals(1, f.users.count)
    }

    @Test
    fun `하나를 꺼내도 다른 저장소는 만들어지지 않는다`() {
        val f = Fixture()

        f.container.questRepository

        assertEquals(1, f.quests.count)
        assertEquals(0, f.users.count)
        assertEquals(0, f.store.count)
    }

    @Test
    fun `세 저장소는 각자 자기 생성 함수의 결과를 돌려준다`() {
        val f = Fixture()

        assertEquals(FakeUserRepository::class, f.container.userRepository::class)
        assertEquals(FakeQuestRepository::class, f.container.questRepository::class)
        assertEquals(FakeActiveQuestStore::class, f.container.activeQuestStore::class)
    }
}
