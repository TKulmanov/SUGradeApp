package su.app.repository.model.evaluation.attestation

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attestation")
data class Attestation(
    @PrimaryKey
    var id: String = "",
    var subjectTitle: String = "",
    var att1: Double? = null,
    var att2: Double? = null,
    var final: Double? = null,
    var total: Double? = null
)