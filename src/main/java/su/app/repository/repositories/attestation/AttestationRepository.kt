package su.app.repository.repositories.attestation

import androidx.lifecycle.LiveData
import su.app.repository.model.evaluation.attestation.Attestation

interface AttestationRepository {
    suspend fun getAttestation(): LiveData<List<Attestation>>
}