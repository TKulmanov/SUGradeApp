package su.app.repository.repositories.transcript

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import su.app.repository.model.evaluation.transcript.Transcript
import su.app.repository.network.datasources.transcript.TranscriptRemoteDataSource
import su.app.repository.room.dao.TranscriptDao
import javax.inject.Inject

class TranscriptRepositoryImpl @Inject constructor(
    private val transcriptRemoteDataSource: TranscriptRemoteDataSource,
    private val transcriptDao: TranscriptDao) :
    TranscriptRepository {

    init{
        transcriptRemoteDataSource.apply {
            downloadedTranscript.observeForever { newTranscriptData ->
                persistRetrievedTranscript(newTranscriptData)
            }
        }
    }
    override suspend fun getTranscript(): LiveData<Transcript> {
        return withContext(Dispatchers.IO){
            retrieveTranscript()
            return@withContext transcriptDao.getTranscript()
        }
    }

    private suspend fun retrieveTranscript() {
        transcriptRemoteDataSource.retrieveTranscript()
    }

    private fun persistRetrievedTranscript(retrievedData: Transcript){
        GlobalScope.launch(Dispatchers.IO) {
            transcriptDao.deleteTranscript()
            transcriptDao.insertTranscript(retrievedData)
        }
    }
}