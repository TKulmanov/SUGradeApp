package su.app.repository.repositories.journal

import androidx.lifecycle.LiveData
import su.app.repository.model.journal.Journal

interface JournalRepository {
    suspend fun getJournal(): LiveData<List<Journal>>
}