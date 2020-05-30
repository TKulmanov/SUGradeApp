package su.app.repository.model.evaluation.transcript

data class Semester(
    var semesterPeriod: String ="",
    var semesterCredits: Int = 0,
    var semesterEcts: Int = 0,
    var confirmedSemesterCredits: Int = 0,
    var semesterGpa: Double = 0.0,
    var yearGpa: Double = 0.0,
    var cumulativeGpa: Double = 0.0,
    var subjects: List<Subject>
    )