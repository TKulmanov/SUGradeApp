package su.app.domain

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import su.app.iternal.lazyDeferred
import su.app.repository.repositories.schedule.ScheduleRepositoryImpl
import javax.inject.Inject

class ScheduleViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepositoryImpl
) : ViewModel() {

    val schedule by lazyDeferred {
        scheduleRepository.getSchedule()
    }

}
