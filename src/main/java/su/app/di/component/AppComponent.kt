package su.app.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import su.app.presentation.MainActivity
import su.app.di.App
import su.app.di.module.*
import su.app.presentation.LoginActivity
import su.app.presentation.ui.umkd.UmkdFragment
import su.app.presentation.ui.evaluation.attestation.AttestationFragment
import su.app.presentation.ui.evaluation.transcript.TranscriptFragment
import su.app.presentation.ui.journal.details.JournalDetailsFragment
import su.app.presentation.ui.journal.list.JournalFragment
import su.app.presentation.ui.notification.NewsFragment
import su.app.presentation.ui.schedule.exam.ExamFragment
import su.app.presentation.ui.schedule.schedule.ScheduleFragment
import su.app.presentation.ui.settings.SettingsFragment
import su.app.presentation.ui.umkd.category.CategoryFragment
import su.app.repository.network.datasources.attestation.AttestationRemoteDataSourceImpl
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

@Component(
    modules = [AndroidSupportInjectionModule::class, ViewModelModule::class,
        LocalModule::class,FragmentModule::class,
        RemoteModule::class, NetModule::class, ViewModule::class,
        RemoteSourceModule::class, LocalSourceModule::class, RepositoryModule::class]
)
@Singleton
interface AppComponent {
    fun inject(application: App)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        fun setNetModule(netModule: NetModule): Builder

        fun setLocalModule(localModule: LocalModule): Builder

        @BindsInstance
        fun bindApplication(application: Application): Builder
    }

    fun provideJournalDao(): JournalDao

    fun provideScheduleDao(): ScheduleDao

    fun provideExamDao(): ExamDao

    fun provideAttestationDao(): AttestationDao

    fun provideTranscriptDao(): TranscriptDao

    fun provideUserDao(): UserDao

    fun provideJournalRemoteDataSource(): JournalRemoteDataSourceImpl

    fun provideJournalRepository(): JournalRepositoryImpl

    fun provideScheduleRemoteDataSource(): ScheduleRemoteDataSourceImpl

    fun provideScheduleRepository(): ScheduleRepositoryImpl

    fun provideExamRemoteDataSource(): ExamRemoteDataSourceImpl

    fun provideExamRepository(): ExamRepositoryImpl

    fun provideAttestationRemoteDataSource(): AttestationRemoteDataSourceImpl

    fun provideAttestationRepository(): AttestationRepositoryImpl

    fun provideTranscriptRemoteDataSource(): TranscriptRemoteDataSourceImpl

    fun provideTranscriptRepository(): TranscriptRepositoryImpl

    fun provideNewsRemoteDataSource(): NewsRemoteDataSourceImpl

    fun provideNewsRepository(): NewsRepositoryImpl

    fun provideUmkdRemoteDataSource(): UmkdRemoteDataSourceImpl

    fun provideUmkdRepository(): UmkdRepositoryImpl

    fun provideAuthRemoteDataSource(): UserRepositoryImpl

    fun provideAuthRepository(): UserRepositoryImpl

}

@Module
abstract class ViewModule {
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeActivityAndroidInjector(): MainActivity

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeLoginActivityAndroidInjector(): LoginActivity
}

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeJournalFragmentViewModelInjector(): JournalFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeScheduleFragmentViewModelInjector(): ScheduleFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeExamFragmentViewModelInjector(): ExamFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeJournalDetailsFragmentViewModelInjector(): JournalDetailsFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeAttestetionFragmentViewModelInjector(): AttestationFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeTranscriptFragmentViewModelInjector(): TranscriptFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeNewsFragmentViewModelInjector(): NewsFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeUmkdFragmentViewModelInjector(): UmkdFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeCategoryFragmentViewModelInjector(): CategoryFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeSettingsFragmentViewModelInjector(): SettingsFragment
}
