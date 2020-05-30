package su.app.domain

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import su.app.iternal.lazyDeferred
import su.app.repository.repositories.transcript.TranscriptRepositoryImpl
import javax.inject.Inject

class TranscriptViewModel @Inject constructor(
    private val transcriptRepository: TranscriptRepositoryImpl
) : ViewModel() {

    val transcript by lazyDeferred {
        transcriptRepository.getTranscript()
    }

}