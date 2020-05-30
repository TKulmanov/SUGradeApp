package su.app.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import su.app.repository.SessionManager
import su.app.repository.network.AuthInterceptor
import su.app.repository.network.ConnectivityInterceptor
import su.app.repository.network.ConnectivityInterceptorImpl
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetModule(private val context: Context) {
    @Provides
    @Singleton
    @Named("ConnectivityInterceptor")
    fun provideInterceptor(): ConnectivityInterceptor = ConnectivityInterceptorImpl(context)
    
    
    @Provides
    @Singleton
    @Named("AuthInterceptor")
    fun provideAuthInterceptor(): AuthInterceptor = AuthInterceptor(context)


    @Provides
    @Singleton
    fun provideSessionManager():SessionManager = SessionManager(context)
}