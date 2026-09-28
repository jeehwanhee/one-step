package com.jeepark.onestep.di

import com.jeepark.onestep.data.repository.DeleteResult
import com.jeepark.onestep.data.repository.FakeActiveQuestStore
import com.jeepark.onestep.data.repository.FakeAuthRepository
import com.jeepark.onestep.data.repository.FakeQuestRepository
import com.jeepark.onestep.data.repository.FakeSettingsRepository
import com.jeepark.onestep.data.repository.FakeUserRepository
import com.jeepark.onestep.data.model.User
import com.jeepark.onestep.util.FakeLocationProvider
import com.jeepark.onestep.util.FakeNotificationScheduler
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
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
    fun `컨테이너를 만들 때는 아무 것도 만들지 않는다`() {
        val f = Fixture()

        assertEquals(listOf(0, 0, 0, 0, 0, 0, 0), f.counts)
    }

    @Test
    fun `항목은 처음 꺼낼 때 한 번만 만들어지고 이후에는 같은 인스턴스를 돌려준다`() {
        val f = Fixture()

        val first = f.container.userRepository
        val second = f.container.userRepository

        assertSame(first, second)
        assertEquals(1, f.users.count)
    }

    @Test
    fun `하나를 꺼내도 다른 항목은 만들어지지 않는다`() {
        val f = Fixture()

        f.container.questRepository

        assertEquals(listOf(0, 0, 1, 0, 0, 0, 0), f.counts)
    }

    @Test
    fun `각 항목은 자기 생성 함수의 결과를 돌려준다`() {
        val f = Fixture()

        assertEquals(FakeAuthRepository::class, f.container.authRepository::class)
        assertEquals(FakeUserRepository::class, f.container.userRepository::class)
        assertEquals(FakeQuestRepository::class, f.container.questRepository::class)
        assertEquals(FakeActiveQuestStore::class, f.container.activeQuestStore::class)
        assertEquals(FakeSettingsRepository::class, f.container.settingsRepository::class)
        assertEquals(FakeNotificationScheduler::class, f.container.notificationScheduler::class)
        assertEquals(FakeLocationProvider::class, f.container.locationProvider::class)
    }

    @Test
    fun `계정 서비스를 꺼내면 서비스가 쓰는 네 항목만 만들어진다`() {
        val f = Fixture()

        f.container.accountService

        // 인증·사용자·설정·알림 예약. 퀘스트 저장소와 진행 중 퀘스트 보관소는 쓰지 않으므로 만들지 않는다
        assertEquals(listOf(1, 1, 0, 0, 1, 1, 0), f.counts)
    }

    @Test
    fun `계정 서비스는 컨테이너가 가진 것과 같은 인스턴스들을 조합해서 쓴다`() = runTest {
        val f = Fixture()

        val result = f.container.accountService.deleteAccount()

        // 서비스가 컨테이너와 다른 인스턴스를 만들어 썼다면 아래 저장소들에 결과가 반영되지 않는다
        assertEquals(DeleteResult.Success, result)
        assertNull((f.container.userRepository as FakeUserRepository).user)
        assertEquals(1, (f.container.authRepository as FakeAuthRepository).signOutCount)
        assertEquals(0L, f.container.settingsRepository.lastAccessMillis)
        assertFalse((f.container.notificationScheduler as FakeNotificationScheduler).isScheduled)
        assertEquals(listOf(1, 1, 0, 0, 1, 1, 0), f.counts) // 서비스 안팎에서 써도 각각 한 번만 만들어진다
    }

    @Test
    fun `계정 서비스도 한 번만 만들어진다`() {
        val f = Fixture()

        assertSame(f.container.accountService, f.container.accountService)
    }
}
