package su.app.domain

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import su.app.iternal.lazyDeferred
import su.app.repository.repositories.news.NewsRepositoryImpl
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepositoryImpl
): ViewModel() {

    val news by lazyDeferred {
         newsRepository.getNews()
    }

}
