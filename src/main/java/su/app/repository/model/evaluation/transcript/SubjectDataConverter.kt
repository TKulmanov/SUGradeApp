package su.app.repository.model.evaluation.transcript

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
class SubjectDataConverter{

    var gson = Gson()

    @TypeConverter
    fun fromSubjectList(subjects: List<Subject>?): String? {
        if (subjects == null) {
            return null
        }
        var type  = object : TypeToken<List<Subject>>(){}.type
        return this.gson.toJson(subjects, type)
    }

    @TypeConverter
    fun toSubjectList(data: String?): List<Subject>? {
        if (data == null) {
            return null
        }
        val type = object : TypeToken<List<Subject>>(){}.type
        return this.gson.fromJson(data, type)
    }
}