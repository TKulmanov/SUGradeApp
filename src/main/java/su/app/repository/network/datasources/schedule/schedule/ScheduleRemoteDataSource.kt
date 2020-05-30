package su.app.repository.network.datasources.schedule.schedule

import androidx.lifecycle.LiveData
import su.app.repository.model.schedule.Schedule

interface ScheduleRemoteDataSource {
    val downloadedSchedule: LiveData<List<Schedule>>

    suspend fun retrieveSchedule( )
}