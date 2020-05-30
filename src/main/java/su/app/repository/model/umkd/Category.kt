package su.app.repository.model.umkd

import java.io.Serializable

data class Category(
    val categoryId: Int,
    val categoryType: String,
    val disciplineId: String,
    val files: List<File>
): Serializable