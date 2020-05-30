package su.app.domain

import androidx.lifecycle.ViewModel
import su.app.iternal.lazyDeferred
import su.app.repository.repositories.schedule.exam.ExamRepositoryImpl
import javax.inject.Inject

class ExamViewModel @Inject constructor(
    private val examRepository: ExamRepositoryImpl
) : ViewModel() {

    val exam by lazyDeferred {
        examRepository.getExam()
    }
}
