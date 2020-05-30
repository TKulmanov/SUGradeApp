package su.app.repository.model.journal

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.reflect.typeOf

class JournalDataConverter {
        var gson = Gson()

        @TypeConverter
        fun fromJournalDatesList(journalDetail: List<Dates>?): String? {
            if (journalDetail == null) {
                return null
            }
            var type = object : TypeToken<List<Dates>>(){}.type
            return this.gson.toJson(journalDetail, type)
        }

        @TypeConverter
        fun toJournalDatesList(data: String?): List<Dates>? {
            if (data == null) {
                return null
            }
            var type = object : TypeToken<List<Dates>>(){}.type
            return this.gson.fromJson(data, type)
        }
}