package su.app.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import su.app.repository.model.schedule.Exam

@Dao
interface ExamDao {
    @Query("SELECT * FROM exam")
    fun getExam(): LiveData<List<Exam>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExam(journals: List<Exam>)

    @Query("DELETE FROM exam")
    fun deleteExam()
}