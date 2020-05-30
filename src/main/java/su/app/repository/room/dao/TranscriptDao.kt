package su.app.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import su.app.repository.model.evaluation.transcript.Transcript
import su.app.repository.model.journal.Journal

@Dao
interface TranscriptDao {
        @Query("SELECT * FROM transcript")
        fun getTranscript(): LiveData<Transcript>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertTranscript(transcript: Transcript)

        @Query("DELETE FROM transcript")
        fun deleteTranscript()
}