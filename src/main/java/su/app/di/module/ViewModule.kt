package su.app.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import su.app.ViewModelFactory
import su.app.domain.*
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(JournalViewModel::class)
    abstract fun bindJournalViewModel(journalViewModel: JournalViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ScheduleViewModel::class)
    abstract fun bindScheduleViewModel(scheduleViewModel: ScheduleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExamViewModel::class)
    abstract fun bindExamViewModel(examViewModel: ExamViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(JournalDetailsViewModel::class)
    abstract fun bindJournalDetailsViewModel(journalDetailsViewModel: JournalDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AttestationViewModel::class)
    abstract fun bindAttestationViewModel(attestationViewModel: AttestationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TranscriptViewModel::class)
    abstract fun bindTransctriptViewModel(transcriptViewModel: TranscriptViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsViewModel(newsViewModel: NewsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UmkdViewModel::class)
    abstract fun bindUmkdViewModel(umkdViewModel: UmkdViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    abstract fun bindCategoryViewModel(categoryViewModel: CategoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: UserViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}