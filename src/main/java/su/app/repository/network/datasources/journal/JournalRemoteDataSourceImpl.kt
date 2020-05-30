package su.app.repository.network.datasources.journal

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import su.app.repository.model.journal.Journal
import su.app.repository.network.ApiService
import java.lang.Exception
import javax.inject.Inject

class JournalRemoteDataSourceImpl @Inject constructor(private val apiService: ApiService): JournalRemoteDataSource {
    private val _downloadedJournal = MutableLiveData<List<Journal>>()
    override val downloadedJournal: LiveData<List<Journal>>
        get() = _downloadedJournal
    override suspend fun retrieveJournal(){
        try{
            val fetchedJournal = apiService.getJournal().await()
            _downloadedJournal.postValue(fetchedJournal)
        }catch (e: Exception){
            Log.e("JournalSource","Connection problems")
        }
    }
}