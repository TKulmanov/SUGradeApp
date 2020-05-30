package su.app.repository.repositories.umkd

import androidx.lifecycle.LiveData
import su.app.repository.model.umkd.Category
import su.app.repository.model.umkd.Discipline

interface UmkdRepository {
    suspend fun getDisciplines(): LiveData<List<Discipline>>
    suspend fun getCategories(disciplineId: String): LiveData<List<Category>>
}