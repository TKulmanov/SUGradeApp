package su.app.di.module

import dagger.Module
import dagger.Provides
import su.app.repository.SessionManager
import su.app.repository.network.datasources.attestation.AttestationRemoteDataSourceImpl
import su.app.repository.network.datasources.auth.AuthRemoteDataSourceImpl
import su.app.repository.network.datasources.journal.JournalRemoteDataSourceImpl
import su.app.repository.network.datasources.notification.news.NewsRemoteDataSourceImpl
import su.app.repository.network.datasources.schedule.exam.ExamRemoteDataSourceImpl
import su.app.repository.network.datasources.schedule.schedule.ScheduleRemoteDataSourceImpl
import su.app.repository.network.datasources.transcript.TranscriptRemoteDataSourceImpl
import su.app.repository.network.datasources.umkd.UmkdRemoteDataSourceImpl
import su.app.repository.repositories.UserRepositoryImpl
import su.app.repository.repositories.attestation.AttestationRepositoryImpl
import su.app.repository.repositories.journal.JournalRepositoryImpl
import su.app.repository.repositories.news.NewsRepositoryImpl
import su.app.repository.repositories.schedule.ScheduleRepositoryImpl
import su.app.repository.repositories.schedule.exam.ExamRepositoryImpl
import su.app.repository.repositories.transcript.TranscriptRepositoryImpl
import su.app.repository.repositories.umkd.UmkdRepositoryImpl
import su.app.repository.room.dao.*
import javax.inject.Singleton


@Module(includes = [RemoteModule::class, LocalModule::class])
class RepositoryModule {

    @Provides
    @Singleton
    fun provideJournalRepository(
        journalRemoteDataSourceImpl: JournalRemoteDataSourceImpl,
        journalDao: JournalDao
    ): JournalRepositoryImpl =
        JournalRepositoryImpl(
            journalRemoteDataSourceImpl, journalDao
        )

    @Provides
    @Singleton
    fun provideScheduleRepository(
        scheduleRemoteDataSourceImpl: ScheduleRemoteDataSourceImpl,
        scheduleDao: ScheduleDao
    ): ScheduleRepositoryImpl =
        ScheduleRepositoryImpl(
            scheduleRemoteDataSourceImpl, scheduleDao
        )

    @Provides
    @Singleton
    fun provideExamRepository(
        examRemoteDataSourceImpl: ExamRemoteDataSourceImpl,
        examDao: ExamDao
    ): ExamRepositoryImpl =
        ExamRepositoryImpl(
            examRemoteDataSourceImpl, examDao
        )

    @Provides
    @Singleton
    fun provideAttestationRepository(
        attestationRemoteDataSourceImpl: AttestationRemoteDataSourceImpl,
        attestationDao: AttestationDao
    ): AttestationRepositoryImpl =
        AttestationRepositoryImpl(
            attestationRemoteDataSourceImpl, attestationDao
        )

    @Provides
    @Singleton
    fun provideTranscriptRepository(
        transcriptRemoteDataSourceImpl: TranscriptRemoteDataSourceImpl,
        transcriptDao: TranscriptDao
    ): TranscriptRepositoryImpl =
        TranscriptRepositoryImpl(
            transcriptRemoteDataSourceImpl, transcriptDao
        )

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsRemoteDataSourceImpl: NewsRemoteDataSourceImpl,
        newsDao: NewsDao
    ): NewsRepositoryImpl =
        NewsRepositoryImpl(
            newsRemoteDataSourceImpl, newsDao
        )

    @Provides
    @Singleton
    fun provideUmkdRepository(
        umkdRemoteDataSourceImpl: UmkdRemoteDataSourceImpl
        ): UmkdRepositoryImpl =
        UmkdRepositoryImpl(
            umkdRemoteDataSourceImpl
        )

    @Provides
    @Singleton
    fun provideAuthRepository(
        authRemoteDataSourceImpl: AuthRemoteDataSourceImpl,
        userDao: UserDao,
        journalDao: JournalDao,
        scheduleDao: ScheduleDao,
        attestationDao: AttestationDao,
        transcriptDao: TranscriptDao,
        newsDao: NewsDao,
        sessionManager: SessionManager
    ): UserRepositoryImpl =
        UserRepositoryImpl(
            authRemoteDataSourceImpl, userDao, journalDao,scheduleDao,attestationDao,transcriptDao,newsDao,sessionManager
        )

}