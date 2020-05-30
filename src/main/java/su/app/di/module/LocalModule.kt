package su.app.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import su.app.repository.room.AppDatabase
import su.app.repository.room.dao.*
import javax.inject.Singleton

@Module(includes = [LocalModule::class])
abstract class LocalSourceModule

@Module
class LocalModule(private val context: Context) {
    companion object {
        const val databaseName = "database"
    }

    @Provides
    @Singleton
    fun provideDataBase(): AppDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            databaseName
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideJournalDao(appDatabase: AppDatabase): JournalDao =
        appDatabase.journalDao()

    @Provides
    @Singleton
    fun provideScheduleDao(appDatabase: AppDatabase): ScheduleDao =
        appDatabase.scheduleDao()

    @Provides
    @Singleton
    fun provideExamDao(appDatabase: AppDatabase): ExamDao =
        appDatabase.examDao()

    @Provides
    @Singleton
    fun provideAttestationDao(appDatabase: AppDatabase): AttestationDao =
        appDatabase.attestationDao()

    @Provides
    @Singleton
    fun provideTranscriptDao(appDatabase: AppDatabase): TranscriptDao =
        appDatabase.transcriptDao()

    @Provides
    @Singleton
    fun provideNewsDao(appDatabase: AppDatabase): NewsDao =
        appDatabase.newsDao()

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao =
        appDatabase.userDao()

}