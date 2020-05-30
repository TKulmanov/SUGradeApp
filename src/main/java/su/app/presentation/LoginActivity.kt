package su.app.presentation

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import su.app.R
import su.app.databinding.ActivityLoginBinding
import su.app.domain.UserViewModel
import javax.inject.Inject

class LoginActivity:   DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private  lateinit var  userViewModel: UserViewModel

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)

        userViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)
        binding.vm = userViewModel
        isAuthenticated()
    }

    private fun isAuthenticated() {
        userViewModel.isAuthenticated().observe(this@LoginActivity, Observer {userToken->
            if(userToken == null || userToken == "")return@Observer
            if(userToken != null && userToken!="") {
                val intent = Intent(this, MainActivity::class.java)
                this.startActivity(intent)
            }
         }
        )
    }


}
