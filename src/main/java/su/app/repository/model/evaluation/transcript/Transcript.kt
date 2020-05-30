package su.app.repository.model.evaluation.transcript

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "transcript")
data class Transcript(
    @PrimaryKey
    var id: Int = 1,
    var semesters: List<Semester>
)