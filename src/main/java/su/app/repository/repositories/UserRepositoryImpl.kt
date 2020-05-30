package su.app.repository.repositories

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import su.app.repository.SessionManager
import su.app.repository.model.LoginRequest
import su.app.repository.model.User
import su.app.repository.network.datasources.auth.AuthRemoteDataSource
import su.app.repository.room.dao.*
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val userDao: UserDao,
    private val journalDao: JournalDao,
    private val scheduleDao: ScheduleDao,
    private val attestationDao: AttestationDao,
    private val transcriptDao: TranscriptDao,
    private val newsDao: NewsDao,
    private val sessionManager: SessionManager): UserRepository {

//    override suspend fun getUser(loginRequest: LoginRequest): User {
//        return withContext(Dispatchers.IO){
//            retrieveUser(loginRequest)
//            val user = authRemoteDataSource.response.user
//            persistUser(user)
//            sessionManager.saveAuthToken(user.token)
//            println(user.token)
//            return@withContext userDao.getUser()
//        }
//    }

    override suspend fun getUser(): LiveData<User> {
        return withContext(Dispatchers.IO) {
            return@withContext userDao.getUser()
        }
    }

    override suspend fun login(loginRequest: LoginRequest) {
        val response = authRemoteDataSource.login(loginRequest)
        if(response?.statusCode == 200){
            var user = response.user
            user.token = response.authToken
            persistUser(user)
            sessionManager.saveAuthToken(user.token)
        }else {
            return
        }
    }

    private fun persistUser(user: User){
        GlobalScope.launch(Dispatchers.IO) {
              userDao.insertUser(user)
        }
    }

    fun exit(){
        GlobalScope.launch(Dispatchers.IO) {
            sessionManager.removeAuthToken()
            userDao.deleteUser()
            journalDao.deleteJournals()
            scheduleDao.deleteSchedule()
            attestationDao.deleteAttestations()
            transcriptDao.deleteTranscript()
            newsDao.deleteNews()
        }
    }
}