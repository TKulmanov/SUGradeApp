package su.app.repository.network.datasources.journal

import androidx.lifecycle.LiveData
import su.app.repository.model.journal.Journal

interface JournalRemoteDataSource {
    val downloadedJournal: LiveData<List<Journal>>

    suspend fun retrieveJournal( )
}