package su.app.di

import android.app.Activity
import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import su.app.di.component.AppComponent
import su.app.di.component.DaggerAppComponent
import su.app.di.module.LocalModule
import su.app.di.module.NetModule
import javax.inject.Inject

class App: Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun androidInjector(): AndroidInjector<Any>? = dispatchingAndroidInjector as AndroidInjector<Any>

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        appComponent = DaggerAppComponent.builder()
            .bindApplication(this)
            .setLocalModule(LocalModule(applicationContext))
            .setNetModule(NetModule(applicationContext))
            .build()
        appComponent.inject(this)

    }
}