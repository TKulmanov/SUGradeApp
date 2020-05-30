package su.app.repository.network.datasources.notification.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import su.app.repository.model.notification.News
import su.app.repository.network.ApiService
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(private val apiService: ApiService):NewsRemoteDataSource {
    private val _downloadedNews = MutableLiveData<List<News>>()
    override val downloadedNews: LiveData<List<News>>
        get() = _downloadedNews
    override suspend fun retrieveNews(){
        try{
            val fetchedNews = apiService.getNews().await()
            _downloadedNews.postValue(fetchedNews)
        }catch (e: Exception){
            Log.e("NewsSource","News connection problem")
        }
    }
}