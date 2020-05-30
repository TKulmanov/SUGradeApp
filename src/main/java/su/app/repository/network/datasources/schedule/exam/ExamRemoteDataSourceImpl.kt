package su.app.repository.network.datasources.schedule.exam

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import su.app.repository.model.schedule.Exam
import su.app.repository.network.ApiService
import javax.inject.Inject

class ExamRemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) : ExamRemoteDataSource {

    private val _downloadedExam = MutableLiveData<List<Exam>>()
    override val downloadedExam: LiveData<List<Exam>>
        get() = _downloadedExam

    override suspend fun retrieveExam(){
        try{
            val fetchedExam = apiService.getExam().await()
            _downloadedExam.postValue(fetchedExam)
        }catch (e: Exception){
            Log.e("ScheduleSource","Schedule connection problem")
        }
    }
}