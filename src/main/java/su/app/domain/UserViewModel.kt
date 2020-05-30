package su.app.domain

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import kotlinx.coroutines.*
import su.app.iternal.lazyDeferred
import su.app.repository.SessionManager
import su.app.repository.model.LoginRequest
import su.app.repository.repositories.UserRepositoryImpl
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val authRepository: UserRepositoryImpl,
    private val sessionManager: SessionManager
): ViewModel(){

    var btnSelected: ObservableBoolean? = null
    var id =  ObservableField<String>()
    var password = ObservableField<String>()
    private val authenticatedUser = MutableLiveData<String>()

    init{
        onIdChanged(id)
        onPasswordChanged(password)
    }

    fun login() {
        viewModelScope.launch(Dispatchers.IO){
            val id = this@UserViewModel.id.get()!!
            val password = this@UserViewModel.password.get()!!
            authRepository.login(LoginRequest(id,password))
            authenticatedUser.postValue(sessionManager.fetchAuthToken())
        }
    }

    fun isAuthenticated():MutableLiveData<String> {
        authenticatedUser.value = sessionManager.fetchAuthToken()
        return authenticatedUser
    }

    val user by lazyDeferred {
        authRepository.getUser()
    }

    fun exit() = authRepository.exit()


    private fun onIdChanged(id: ObservableField<String>?) {
        btnSelected?.set(id?.get()?.length!! >= 8)
    }

    private fun onPasswordChanged(password: ObservableField<String>?) {
        btnSelected?.set(password?.toString()?.isNotEmpty()!!)
    }

}
