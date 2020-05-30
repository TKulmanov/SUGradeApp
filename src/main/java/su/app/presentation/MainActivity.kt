package su.app.presentation

import android.content.Intent
import android.content.res.AssetManager
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import dagger.android.support.DaggerAppCompatActivity
import su.app.R
import su.app.databinding.ActivityMainBinding
import su.app.domain.UserViewModel
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var userViewModel: UserViewModel

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    private lateinit var toolbar:Toolbar
    private lateinit var appBarConfiguration:AppBarConfiguration
    private lateinit var bottomNavView:BottomNavigationView
    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var content: View
    private lateinit var toggleButton:ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        userViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)
        binding.vm = userViewModel


        drawerLayout = binding.drawerLayout
        content = binding.mainArea


        navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.drawer_layout,R.id.navigation_journal_details,R.id.navigation_journal,
                R.id.navigation_schedule,R.id.navigation_evaluation,
                R.id.navigation_notifications,R.id.navigation_settings,
                R.id.navigation_umkd,R.id.navigation_files,R.id.navigation_umkd,R.id.navigation_categories
                ), drawerLayout)



        initToolbar()
        initNavDrawer()
        initBottomNavMenu()
        scaleOnDrawerSlide()
        visibilityNavElements(navController)

    }

    private fun initToolbar(){
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController,appBarConfiguration)
    }

    private fun initBottomNavMenu(){
        bottomNavView = binding.bottomNavView
        bottomNavView.setupWithNavController(navController)
    }

    private fun initNavDrawer(){
        navView = binding.drawerNavView

        navView.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }




    private fun visibilityNavElements(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_settings -> bottomNavView.visibility = View.GONE
                R.id.navigation_journal_details -> {
                    toolbar.visibility = View.GONE
                    bottomNavView.visibility = View.GONE
                }
                R.id.navigation_umkd -> bottomNavView.visibility = View.GONE
                R.id.navigation_categories -> bottomNavView.visibility = View.GONE
                R.id.navigation_files -> bottomNavView.visibility = View.GONE
                R.id.navigation_file -> bottomNavView.visibility = View.GONE
                else -> {
                    toolbar.visibility = View.VISIBLE
                    bottomNavView.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun scaleOnDrawerSlide(){
        toggleButton = object : ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close){
            override fun onDrawerSlide(drawerView: View,slideOffset: Float) {
                super.onDrawerSlide(drawerView,0.0f)
                val diffScaledOffset = slideOffset * (1 - 0.8f)
                val offsetScale = 1 - diffScaledOffset
                content.scaleX = offsetScale
                content.scaleY = offsetScale

                val xOffset = drawerView.width * slideOffset
                val xOffsetDiff = content.width * diffScaledOffset / 2
                val xTranslation = xOffset - xOffsetDiff
                content.translationX = xTranslation
                drawerLayout.setScrimColor(Color.TRANSPARENT)
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerSlide(drawerView,0.0f)
            }

        }
        drawerLayout.addDrawerListener(toggleButton)
        toggleButton.syncState()
    }

    override fun getAssets(): AssetManager {
        return resources.assets
    }
}


