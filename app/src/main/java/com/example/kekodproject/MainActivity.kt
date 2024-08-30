package com.example.kekodproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.kekodproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DataBinding kullanarak layout'u bağla
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // NavHostFragment'i almak için kodu güncelle
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as? NavHostFragment
        val navController = navHostFragment?.navController

        // NavController varsa BottomNavigationView'i bağla
        navController?.let {
            binding.bottomNav.setupWithNavController(it)
        } ?: run {
            // Log veya hata mesajı ekleyerek eksik NavHostFragment durumunu işleyin
            throw IllegalStateException("NavHostFragment not found")
        }
    }
}