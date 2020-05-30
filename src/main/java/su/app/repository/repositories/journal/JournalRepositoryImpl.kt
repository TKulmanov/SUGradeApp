package su.app.repository.repositories.journal

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import su.app.repository.model.journal.Journal
import su.app.repository.network.datasources.journal.JournalRemoteDataSource
import su.app.repository.room.dao.JournalDao
import javax.inject.Inject

class JournalRepositoryImpl @Inject constructor(
    private val journalRemoteDataSource: JournalRemoteDataSource,
    private val journalDao: JournalDao
) : JournalRepository {

    init{
        journalRemoteDataSource.apply {
            downloadedJournal.observeForever { newJournalData ->
                persistRetrievedJournal(newJournalData)
            }
        }
    }
    override suspend fun getJournal(): LiveData<List<Journal>> {
        return withContext(Dispatchers.IO){
            retrieveJournal()
            return@withContext journalDao.getJournal()
        }
    }

    private suspend fun retrieveJournal() {
        journalRemoteDataSource.retrieveJournal()
    }

    private fun persistRetrievedJournal(retrievedData: List<Journal>){
        GlobalScope.launch(Dispatchers.IO) {
            journalDao.deleteJournals()
            journalDao.insertJournal(retrievedData)
        }
    }
}