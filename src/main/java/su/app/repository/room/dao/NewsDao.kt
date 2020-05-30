package su.app.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import su.app.repository.model.notification.News

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getNews(): LiveData<List<News>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(journals: List<News>)

    @Query("DELETE FROM news")
    fun deleteNews()
}