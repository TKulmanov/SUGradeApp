package su.app.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import su.app.repository.model.evaluation.attestation.Attestation

@Dao
interface AttestationDao {
    @Query("SELECT * FROM attestation")
    fun getAttestation(): LiveData<List<Attestation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(lessons: List<Attestation>)

    @Query("DELETE FROM attestation")
    fun deleteAttestations()
}