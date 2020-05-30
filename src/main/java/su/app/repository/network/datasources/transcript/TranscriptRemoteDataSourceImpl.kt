package su.app.repository.network.datasources.transcript

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import su.app.repository.model.evaluation.transcript.Transcript
import su.app.repository.network.ApiService
import javax.inject.Inject

class TranscriptRemoteDataSourceImpl  @Inject constructor(private val apiService: ApiService): TranscriptRemoteDataSource {
        private val _downloadedTranscript = MutableLiveData<Transcript>()
        override val downloadedTranscript: LiveData<Transcript>
            get() = _downloadedTranscript
        override suspend fun retrieveTranscript(){
            try{
                val fetchedTranscript = apiService.getTranscript().await()
                _downloadedTranscript.postValue(fetchedTranscript)
            }catch (e: Exception){
                Log.e("TranscriptSource","Transcript connection problem")
            }
        }
}
