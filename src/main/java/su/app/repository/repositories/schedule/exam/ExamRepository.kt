package su.app.repository.repositories.schedule.exam

import androidx.lifecycle.LiveData
import su.app.repository.model.schedule.Exam

interface ExamRepository {
    suspend fun getExam(): LiveData<List<Exam>>
}