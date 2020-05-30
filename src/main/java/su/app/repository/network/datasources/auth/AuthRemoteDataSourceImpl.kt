package su.app.repository.network.datasources.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import su.app.repository.model.LoginRequest
import su.app.repository.model.LoginResponse
import su.app.repository.model.User
import su.app.repository.network.ApiService
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(private val apiService: ApiService): AuthRemoteDataSource {
//    private var responseTemp = LoginResponse(0,User())
//    override val response: LoginResponse
//        get() = responseTemp
    override suspend fun login(request: LoginRequest ): LoginResponse?{
        try{
            return apiService.login(request).await()
        }catch (e: Exception){
            Log.e("AuthSource","Auth connection problem")
        }
        return null
    }
}