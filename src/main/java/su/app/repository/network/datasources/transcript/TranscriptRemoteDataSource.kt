package su.app.repository.network.datasources.transcript

import androidx.lifecycle.LiveData
import su.app.repository.model.evaluation.transcript.Transcript


interface TranscriptRemoteDataSource {
        val downloadedTranscript: LiveData<Transcript>

        suspend fun retrieveTranscript( )
}