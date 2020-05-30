package su.app.repository.repositories

import androidx.lifecycle.LiveData
import su.app.repository.model.LoginRequest
import su.app.repository.model.User

interface UserRepository {
    suspend fun getUser(): LiveData<User>
    suspend fun login(loginRequest: LoginRequest)
}