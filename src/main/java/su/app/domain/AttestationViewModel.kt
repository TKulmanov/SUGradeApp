package su.app.domain

import androidx.lifecycle.ViewModel
import javax.inject.Inject
import su.app.iternal.lazyDeferred
import su.app.repository.repositories.attestation.AttestationRepositoryImpl

class AttestationViewModel @Inject constructor(
    private val attestationRepository: AttestationRepositoryImpl
): ViewModel(){


    val attestation by lazyDeferred {
        attestationRepository.getAttestation()
    }

}