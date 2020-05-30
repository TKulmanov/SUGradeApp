package su.app.repository.network.datasources.notification.news

import androidx.lifecycle.LiveData
import su.app.repository.model.notification.News

interface NewsRemoteDataSource {
    val downloadedNews: LiveData<List<News>>

    suspend fun retrieveNews()
}