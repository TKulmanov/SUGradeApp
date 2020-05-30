package su.app.repository.model.evaluation.transcript

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SemesterDataConverter{
    var gson = Gson()

    @TypeConverter
    fun fromSemesterList(semesters:  List<Semester>?): String? {
        if (semesters == null) {
            return null
        }
        var type = object : TypeToken<List<Semester>>(){}.type
        return this.gson.toJson(semesters, type)
    }

    @TypeConverter
    fun toSemesterList(data: String?): List<Semester>? {
        if (data == null) {
            return null
        }
        var type = object : TypeToken<List<Semester>>(){}.type
        return this.gson.fromJson(data, type)
    }
}