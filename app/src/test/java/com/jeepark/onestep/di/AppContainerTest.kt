package com.jeepark.onestep.di

import com.jeepark.onestep.data.repository.FakeActiveQuestStore
import com.jeepark.onestep.data.repository.FakeAuthRepository
import com.jeepark.onestep.data.repository.FakeQuestRepository
import com.jeepark.onestep.data.repository.FakeSettingsRepository
import com.jeepark.onestep.data.repository.FakeUserRepository
import com.jeepark.onestep.domain.model.User
import com.jeepark.onestep.platform.location.FakeLocationProvider
import com.jeepark.onestep.platform.notification.FakeNotificationScheduler
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
        val auth = Counted { FakeAuthRepository() }
        val users = Counted { FakeUserRepository(user = User(uid = "uid-1")) }
        val quests = Counted { FakeQuestRepository() }
        val store = Counted { FakeActiveQuestStore() }
        val settings = Counted { FakeSettingsRepository(lastAccessMillis = 1_000L) }
        val scheduler = Counted { FakeNotificationScheduler() }
        val location = Counted { FakeLocationProvider() }

        val container = AppContainer(
            createAuthRepository = auth.provider(),
            createUserRepository = users.provider(),
            createQuestRepository = quests.provider(),
            createActiveQuestStore = store.provider(),
            createSettingsRepository = settings.provider(),
            createNotificationScheduler = scheduler.provider(),
            createLocationProvider = location.provider(),
        )

        val counts
            get() = listOf(auth.count, users.count, quests.count, store.count, settings.count, scheduler.count, location.count)
    }

    @Test
    fun `항목은 처음 꺼낼 때 한 번만 만들어지고 이후에는 같은 인스턴스를 돌려준다`() {
        val f = Fixture()

        val first = f.container.userRepository
        val second = f.container.userRepository

        assertSame(first, second)
        assertEquals(1, f.users.count)
    }
}
