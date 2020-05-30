package su.app.repository.network.datasources.attestation

import androidx.lifecycle.LiveData
import su.app.repository.model.evaluation.attestation.Attestation

interface AttestationRemoteDataSource {
    val downloadedAttestation: LiveData<List<Attestation>>

    suspend fun retrieveAttestation( )
}