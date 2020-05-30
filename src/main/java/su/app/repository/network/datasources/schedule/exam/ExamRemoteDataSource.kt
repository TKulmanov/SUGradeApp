package su.app.repository.network.datasources.schedule.exam

import androidx.lifecycle.LiveData
import su.app.repository.model.schedule.Exam

interface ExamRemoteDataSource {
    val downloadedExam: LiveData<List<Exam>>

    suspend fun retrieveExam( )
}