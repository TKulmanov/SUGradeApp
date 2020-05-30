package su.app.repository.network.datasources.auth

import su.app.repository.model.LoginRequest
import su.app.repository.model.LoginResponse

interface AuthRemoteDataSource {
//    val response: LoginResponse

    suspend fun login(request: LoginRequest):LoginResponse?
}