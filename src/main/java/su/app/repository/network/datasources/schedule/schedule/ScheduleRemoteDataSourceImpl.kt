package su.app.repository.network.datasources.schedule.schedule

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import su.app.repository.model.schedule.Schedule
import su.app.repository.network.ApiService
import javax.inject.Inject

class ScheduleRemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) : ScheduleRemoteDataSource {

    private val _downloadedSchedule = MutableLiveData<List<Schedule>>()
    override val downloadedSchedule: LiveData<List<Schedule>>
        get() = _downloadedSchedule
    override suspend fun retrieveSchedule(){
        try{
            val fetchedSchedule = apiService.getSchedule().await()
            _downloadedSchedule.postValue(fetchedSchedule)
        }catch (e: Exception){
            Log.e("ScheduleSource","Schedule connection problem")
        }
    }
}