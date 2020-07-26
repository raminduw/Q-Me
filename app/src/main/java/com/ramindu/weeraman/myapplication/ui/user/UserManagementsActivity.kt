package com.ramindu.weeraman.myapplication.ui.user

import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.ramindu.weeraman.myapplication.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class UserManagementsActivity : AppCompatActivity() {
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        setContentView(R.layout.activity_user)
        supportActionBar?.hide()
        navController = findNavController(R.id.nav_host_user_fragment)
    }

}