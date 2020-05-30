package su.app.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import su.app.repository.model.schedule.Schedule

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM schedule")
    fun getSchedule(): LiveData<List<Schedule>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSchedule(schedules: List<Schedule>)

    @Query("DELETE FROM schedule")
    fun deleteSchedule()
}