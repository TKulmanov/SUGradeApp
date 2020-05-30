package su.app.repository.repositories.schedule

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import su.app.repository.model.schedule.Schedule
import su.app.repository.network.datasources.schedule.schedule.ScheduleRemoteDataSource
import su.app.repository.room.dao.ScheduleDao
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleRemoteDataSource: ScheduleRemoteDataSource,
    private val scheduleDao: ScheduleDao) :
    ScheduleRepository {

    init{
        scheduleRemoteDataSource.apply {
            downloadedSchedule.observeForever { newScheduleData ->
                persistRetrievedSchedule(newScheduleData)
            }
        }
    }
    override suspend fun getSchedule(): LiveData<List<Schedule>> {
        return withContext(Dispatchers.IO){
            retrieveSchedule()
            return@withContext scheduleDao.getSchedule()
        }
    }

    private suspend fun retrieveSchedule() {
        scheduleRemoteDataSource.retrieveSchedule()
    }

    private fun persistRetrievedSchedule(retrievedData: List<Schedule>){
        GlobalScope.launch(Dispatchers.IO) {
            scheduleDao.deleteSchedule()
            scheduleDao.insertSchedule(retrievedData)
        }
    }
}