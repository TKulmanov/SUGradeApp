package su.app.repository.repositories.schedule

import androidx.lifecycle.LiveData
import su.app.repository.model.schedule.Schedule

interface ScheduleRepository {
    suspend fun getSchedule(): LiveData<List<Schedule>>
}