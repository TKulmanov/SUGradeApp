package su.app.repository.repositories.transcript

import androidx.lifecycle.LiveData
import su.app.repository.model.evaluation.transcript.Transcript

interface TranscriptRepository {
        suspend fun getTranscript(): LiveData<Transcript>
}