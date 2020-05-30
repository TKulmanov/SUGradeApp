package su.app.repository.repositories.attestation

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import su.app.presentation.MainActivity
import su.app.repository.model.evaluation.attestation.Attestation
import su.app.repository.network.datasources.attestation.AttestationRemoteDataSource
import su.app.repository.room.dao.AttestationDao
import javax.inject.Inject

class AttestationRepositoryImpl @Inject constructor(
    private val attestationRemoteDataSource: AttestationRemoteDataSource,
    private val attestationDao: AttestationDao
): AttestationRepository {
    init{
        attestationRemoteDataSource.apply {
            downloadedAttestation.observeForever { newAttestationData ->
                persistRetrievedAttestation(newAttestationData)
            }
        }
    }
    override suspend fun getAttestation(): LiveData<List<Attestation>> {
        return withContext(Dispatchers.IO){
            retrieveAttestation()
            return@withContext attestationDao.getAttestation()
        }
    }

    private suspend fun retrieveAttestation() {
        attestationRemoteDataSource.retrieveAttestation()
    }

    private fun persistRetrievedAttestation(retrievedData: List<Attestation>){
        GlobalScope.launch(Dispatchers.IO) {
            attestationDao.deleteAttestations()
            attestationDao.insert(retrievedData)
        }
    }
}