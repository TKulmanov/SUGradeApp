package su.app.repository.model.schedule

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exam(
    @PrimaryKey
    val examId: String = "",
    val examTitle: String = "",
    val examDay: String = "",
    val examStartTime: String = "",
    val examEndTime: String = "",
    val examBuilding: String = "",
    val examRoom: String = "",
    val examinerFullName: String ="",
    val proctorFullName: String
)