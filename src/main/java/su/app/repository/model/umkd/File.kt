package su.app.repository.model.umkd

import java.io.Serializable

data class File(
    val fileId: String,
    val fileName: String,
    val fileUrl: String
): Serializable