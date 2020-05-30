package su.app.repository.model.notification

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class News(
    @PrimaryKey
    var id: Int = 1,
    var title: String = "",
    var body: String = ""
)