package su.app.repository.model.umkd

import androidx.room.Entity

@Entity
data class Discipline(
    val disciplineId: String,
    val disciplineTitle: String,
    val tutorFullName: String
)