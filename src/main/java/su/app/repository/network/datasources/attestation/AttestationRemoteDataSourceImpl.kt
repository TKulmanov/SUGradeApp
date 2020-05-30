package su.app.repository.network.datasources.attestation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import su.app.repository.model.evaluation.attestation.Attestation
import su.app.repository.network.ApiService
import java.lang.Exception
import javax.inject.Inject

class AttestationRemoteDataSourceImpl @Inject constructor(private val apiService: ApiService):
    AttestationRemoteDataSource {
    private val _downloadedAttestation = MutableLiveData<List<Attestation>>()
    override val downloadedAttestation: LiveData<List<Attestation>>
        get() = _downloadedAttestation
    override suspend fun retrieveAttestation(){
        try{
            val fetchedAttestation = apiService.getAttestation().await()
            _downloadedAttestation.postValue(fetchedAttestation)
        }catch (e: Exception){
            Log.e("AttestationSource","Attestation connection problem")
        }
    }
}