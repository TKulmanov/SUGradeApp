package su.app.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import su.app.repository.model.journal.Journal

@Dao
interface JournalDao {
    @Query("SELECT * FROM journal")
    fun getJournal(): LiveData<List<Journal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJournal(journals: List<Journal>)

    @Query("DELETE FROM journal")
    fun deleteJournals()
}