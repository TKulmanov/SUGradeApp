package su.app.repository.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import su.app.repository.SessionManager
import su.app.repository.room.dao.UserDao
import javax.inject.Inject

class AuthInterceptor @Inject constructor(context: Context) : Interceptor{

    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()


//        requestBuilder.addHeader("Authorization", "Bearer " + "${userDao.getUser().token}")

        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }

}