package su.app.repository.model.evaluation.transcript

import androidx.room.Entity

data class Subject(
    var subjectTitle: String = " ",
    var subjectPoint: Double = 0.0,
    var subjectLetter: String = " ",
    var subjectCredits: Int = 0
)