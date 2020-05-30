package su.app.repository.repositories.news

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import su.app.repository.model.notification.News
import su.app.repository.network.datasources.notification.news.NewsRemoteDataSource
import su.app.repository.room.dao.NewsDao
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsDao: NewsDao): NewsRepository {

    init{
        newsRemoteDataSource.apply {
            downloadedNews.observeForever { newNewsData ->
                persistRetrievedNews(newNewsData)
            }
        }
    }
    override suspend fun getNews(): LiveData<List<News>> {
        return withContext(Dispatchers.IO){
            retrieveNews()
            return@withContext newsDao.getNews()
        }
    }

    private suspend fun retrieveNews() {
        newsRemoteDataSource.retrieveNews()
    }

    private fun persistRetrievedNews(retrievedData: List<News>){
        GlobalScope.launch(Dispatchers.IO) {
            newsDao.deleteNews()
            newsDao.insertNews(retrievedData)
        }
    }
}