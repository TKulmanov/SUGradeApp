package su.app.domain

import androidx.lifecycle.ViewModel
import su.app.iternal.lazyDeferred
import su.app.repository.repositories.journal.JournalRepositoryImpl
import javax.inject.Inject

class JournalDetailsViewModel @Inject constructor(
    private val journalRepository: JournalRepositoryImpl
    ) : ViewModel() {

    val attestation by lazyDeferred {
        journalRepository.getJournal()
    }


}
