package su.app.repository.repositories.schedule.exam

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import su.app.repository.model.schedule.Exam
import su.app.repository.network.datasources.schedule.exam.ExamRemoteDataSource
import su.app.repository.room.dao.ExamDao
import javax.inject.Inject

class ExamRepositoryImpl @Inject constructor(
    private val examRemoteDataSource: ExamRemoteDataSource,
    private val examDao: ExamDao
) : ExamRepository {

    init{
        examRemoteDataSource.apply {
            downloadedExam.observeForever { newExamData ->
                persistRetrievedExam(newExamData)
            }
        }
    }
    override suspend fun getExam(): LiveData<List<Exam>> {
        return withContext(Dispatchers.IO){
            retrieveExam()
            return@withContext examDao.getExam()
        }
    }

    private suspend fun retrieveExam() {
        examRemoteDataSource.retrieveExam()
    }

    private fun persistRetrievedExam(retrievedData: List<Exam>){
        GlobalScope.launch(Dispatchers.IO) {
            examDao.getExam()
            examDao.insertExam(retrievedData)
        }
    }
}