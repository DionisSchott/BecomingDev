package com.dionis.becomingdev.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.dionis.becomingdev.R
import com.dionis.becomingdev.databinding.ActivityLoginBinding
import com.dionis.becomingdev.databinding.ActivityNewUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewUserBinding

    private var navHostFragment : NavHostFragment? = null
    private var navController : NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_new_user) as NavHostFragment
        navController = navHostFragment?.navController
    }
}