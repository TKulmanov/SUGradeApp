package su.app.di.module

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import su.app.repository.network.ApiService
import su.app.repository.network.AuthInterceptor
import su.app.repository.network.ConnectivityInterceptor
import su.app.repository.network.datasources.attestation.AttestationRemoteDataSource
import su.app.repository.network.datasources.attestation.AttestationRemoteDataSourceImpl
import su.app.repository.network.datasources.auth.AuthRemoteDataSource
import su.app.repository.network.datasources.auth.AuthRemoteDataSourceImpl
import su.app.repository.network.datasources.journal.JournalRemoteDataSource
import su.app.repository.network.datasources.journal.JournalRemoteDataSourceImpl
import su.app.repository.network.datasources.notification.news.NewsRemoteDataSource
import su.app.repository.network.datasources.notification.news.NewsRemoteDataSourceImpl
import su.app.repository.network.datasources.schedule.exam.ExamRemoteDataSource
import su.app.repository.network.datasources.schedule.exam.ExamRemoteDataSourceImpl
import su.app.repository.network.datasources.schedule.schedule.ScheduleRemoteDataSource
import su.app.repository.network.datasources.schedule.schedule.ScheduleRemoteDataSourceImpl
import su.app.repository.network.datasources.transcript.TranscriptRemoteDataSource
import su.app.repository.network.datasources.transcript.TranscriptRemoteDataSourceImpl
import su.app.repository.network.datasources.umkd.UmkdRemoteDataSource
import su.app.repository.network.datasources.umkd.UmkdRemoteDataSourceImpl
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [RemoteModule::class])
abstract class RemoteSourceModule {

    @Binds
    @Singleton
    abstract fun bindJournalRemoteDataSource(journalRemoteDataSource: JournalRemoteDataSource): JournalRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindScheduleRemoteDataSource(scheduleRemoteDataSource: ScheduleRemoteDataSource): ScheduleRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindExamRemoteDataSource(examRemoteDataSource: ExamRemoteDataSource): ExamRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindAttestationRemoteDataSource(attestationRemoteDataSource: AttestationRemoteDataSource): AttestationRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindTranscriptRemoteDataSource(transcriptRemoteDataSource: TranscriptRemoteDataSource): TranscriptRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindNewsRemoteDataSource(newsRemoteDataSource: NewsRemoteDataSource): NewsRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindUmkdRemoteDataSource(umkdRemoteDataSource: UmkdRemoteDataSource): UmkdRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindAuthRemoteDataSource(authRemoteDataSource: AuthRemoteDataSource): AuthRemoteDataSource

}

@Module
class RemoteModule{


    @Provides
    @Singleton
    fun provideOkHttpClient(
        @Named("ConnectivityInterceptor") connectivityInterceptor: ConnectivityInterceptor,
        @Named("AuthInterceptor") authInterceptor: AuthInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(connectivityInterceptor)
            .addInterceptor(authInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl("https://sufakejson.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()



    @Provides
    @Singleton
    internal fun provideAPI(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideJournalRemoteDataSource(api: ApiService): JournalRemoteDataSourceImpl =
        JournalRemoteDataSourceImpl(api)

    @Provides
    @Singleton
    fun provideScheduleRemoteDataSource(api: ApiService): ScheduleRemoteDataSourceImpl =
        ScheduleRemoteDataSourceImpl(api)

    @Provides
    @Singleton
    fun provideExamRemoteDataSource(api: ApiService): ExamRemoteDataSourceImpl =
        ExamRemoteDataSourceImpl(api)

    @Provides
    @Singleton
    fun provideAttestationRemoteDataSource(api: ApiService): AttestationRemoteDataSourceImpl =
        AttestationRemoteDataSourceImpl(api)

    @Provides
    @Singleton
    fun provideTranscriptRemoteDataSource(api: ApiService): TranscriptRemoteDataSourceImpl =
        TranscriptRemoteDataSourceImpl(api)

    @Provides
    @Singleton
    fun provideNewsRemoteDataSource(api: ApiService): NewsRemoteDataSourceImpl =
        NewsRemoteDataSourceImpl(api)

    @Provides
    @Singleton
    fun provideUmkdRemoteDataSource(api: ApiService): UmkdRemoteDataSourceImpl =
        UmkdRemoteDataSourceImpl(api)

    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(api: ApiService): AuthRemoteDataSourceImpl =
        AuthRemoteDataSourceImpl(api)
}