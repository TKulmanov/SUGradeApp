package su.app.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val id: String = "",
    var token: String = "",
    var fullName: String = "",
    var locale: String =""
)