package su.app.repository.repositories.umkd

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import su.app.repository.model.umkd.Category
import su.app.repository.model.umkd.Discipline
import su.app.repository.network.datasources.umkd.UmkdRemoteDataSource
import javax.inject.Inject

class UmkdRepositoryImpl @Inject constructor(private val umkdRemoteDataSource: UmkdRemoteDataSource) : UmkdRepository {

//    init{
//        umkdRemoteDataSource.apply {
//            downloadedDisciplines.observeForever { newDisciplineData ->
//                persistRetrievedJournal(newDisciplineData)
//            }
//        }
//    }
    override suspend fun getDisciplines(): LiveData<List<Discipline>> {
        return withContext(Dispatchers.IO){
            retrieveDiscipline()
            return@withContext umkdRemoteDataSource.downloadedDisciplines
        }
    }

    private suspend fun retrieveDiscipline() {
        umkdRemoteDataSource.retrieveDisciplines()
    }

    override suspend fun getCategories(disciplineId: String): LiveData<List<Category>> {
        return withContext(Dispatchers.IO){
            retrieveCategories(disciplineId)
            return@withContext umkdRemoteDataSource.downloadedCategories
        }
    }

    private suspend fun retrieveCategories(disciplineId: String) {
        umkdRemoteDataSource.retrieveCategories(disciplineId)
    }

//    private fun persistRetrievedJournal(retrievedData: List<>){
//        GlobalScope.launch(Dispatchers.IO) {
//            journalDao.deleteJournals()
//            journalDao.insertJournal(retrievedData)
//        }
//    }
}