package su.app.repository

import android.content.Context
import android.content.SharedPreferences
import su.app.R

class SessionManager(context: Context) {
    var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = ""
    }

    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    fun removeAuthToken(){
        prefs.edit().clear().apply()
    }
}