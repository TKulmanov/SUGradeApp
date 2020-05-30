package su.app.repository.repositories.news

import androidx.lifecycle.LiveData
import su.app.repository.model.notification.News

interface NewsRepository {
    suspend fun getNews(): LiveData<List<News>>
}