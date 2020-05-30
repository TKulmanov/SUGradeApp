package su.app.repository.network.datasources.umkd

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import su.app.repository.model.umkd.Category
import su.app.repository.model.umkd.Discipline
import su.app.repository.network.ApiService
import javax.inject.Inject

class UmkdRemoteDataSourceImpl @Inject constructor(private val apiService: ApiService):UmkdRemoteDataSource {
    private val _downloadedDisciplines = MutableLiveData<List<Discipline>>()
    override val downloadedDisciplines: LiveData<List<Discipline>>
        get() = _downloadedDisciplines

    private val _downloadedCategories = MutableLiveData<List<Category>>()
    override val downloadedCategories: LiveData<List<Category>>
        get() = _downloadedCategories

    override suspend fun retrieveDisciplines() {
        try{
            val fetchedDisciplines = apiService.getDisciplines().await()
            _downloadedDisciplines.postValue(fetchedDisciplines)
        }catch (e: Exception){
            Log.e("UmkdSource","Discipline connection problem")
        }
    }

    override suspend fun retrieveCategories(disciplineId: String) {
        try{
            val fetchedDisciplines = apiService.getCategories(disciplineId).await()
            _downloadedCategories.postValue(fetchedDisciplines)
        }catch (e: Exception){
            Log.e("UmkdSource","Categories connection problem")
        }
    }
}