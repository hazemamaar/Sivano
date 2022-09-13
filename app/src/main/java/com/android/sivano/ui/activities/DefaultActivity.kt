package com.android.sivano.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.android.sivano.R
import com.android.sivano.databinding.ActivityDefaultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DefaultActivity : AppCompatActivity() {

    private  var _binding: ActivityDefaultBinding ?=null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDefaultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.frag_host) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

       binding.bottomNavigationView.background = null
//        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
        binding.fab.setOnClickListener(View.OnClickListener {
           navController.navigate(R.id.homeFragment)
        })
    }

}