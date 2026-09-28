package com.jeepark.onestep

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.google.firebase.FirebaseApp
import com.jeepark.onestep.di.AppContainer
import com.jeepark.onestep.util.NotificationHelper

/**
 * 프로세스가 시작될 때 한 번 실행되는 앱 초기화 지점.
 * 화면(Activity)이 없어도 알림 워커가 동작할 수 있으므로, 화면과 무관한 초기화는 여기서 한다.
 */
class OneStepApp : Application() {

    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        NotificationHelper.createChannel(this)
        container = AppContainer.create(this)
    }
}

/** Application에 붙어 있는 [AppContainer]. */
val Context.appContainer: AppContainer
    get() = (applicationContext as OneStepApp).container

/** ViewModel 팩토리(`viewModelFactory { initializer { } }`) 안에서 [OneStepApp]을 꺼낸다. */
fun CreationExtras.oneStepApp(): OneStepApp =
    checkNotNull(this[APPLICATION_KEY]) { "ViewModel 생성 정보에 Application이 없습니다" } as OneStepApp
