package su.app.repository.model.schedule

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "schedule")
data class Schedule(
    @PrimaryKey
    var id: Int = 0,
    var lessonTitle: String = "",
    var lessonTutor: String = "",
    var lessonType: String = "",
    var lessonBuilding: String = "",
    var lessonClass: String = "",
    var lessonDay: String = "",
    var lessonStart: String = "",
    var lessonEnd: String = ""
)