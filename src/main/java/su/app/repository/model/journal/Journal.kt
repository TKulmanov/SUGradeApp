package su.app.repository.model.journal

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "journal")
data class Journal(
    @PrimaryKey
    var id: String = "",
    var title: String = "",
    var teacher: String = "",
    var dates: List<Dates>

):Serializable{

    val getGrade: Double
        get() {
            return this.dates.sumByDouble { it.grade }
        }

    val getMissed: Int
        get() {
            var n: Int = 0
            for(i in this.dates){
                if(i.attended) n+=1
            }
            return n
        }
}