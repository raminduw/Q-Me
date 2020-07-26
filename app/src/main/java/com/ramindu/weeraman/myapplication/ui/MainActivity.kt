package com.ramindu.weeraman.myapplication.ui

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ramindu.weeraman.myapplication.R
import com.ramindu.weeraman.myapplication.ui.user.UserLogoutViewModel
import com.ramindu.weeraman.myapplication.ui.user.UserManagementsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView

    private val userLogoutViewModel: UserLogoutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val builder = VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.bottom_nav)
        navController = findNavController(R.id.nav_host_main_fragment)
        setUpBottomNav(navController)

        userLogoutViewModel.userLogoutStatus.observe(this, androidx.lifecycle.Observer {
            it.fold(ifLeft = { error ->
                startUserActivity()
            }, ifRight = { event ->
                startUserActivity()
            })
        })
    }

    private fun setUpBottomNav(navController: NavController) {
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.logout -> {
                userLogoutViewModel.userLogout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun startUserActivity() {
        val intent = Intent(this, UserManagementsActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        startActivity(intent)
        this.finish()
    }

}
