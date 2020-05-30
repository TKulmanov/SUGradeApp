package su.app.repository.network.datasources.umkd

import androidx.lifecycle.LiveData
import su.app.repository.model.umkd.Category
import su.app.repository.model.umkd.Discipline

interface UmkdRemoteDataSource {
    val downloadedDisciplines: LiveData<List<Discipline>>
    val downloadedCategories: LiveData<List<Category>>
//    val downloadedFile: LiveData<File>

    suspend fun retrieveDisciplines()
    suspend fun retrieveCategories(disciplineId: String)
//    suspend fun retrieveFile()
}