package com.example.midtermprojectcerbung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.midtermprojectcerbung.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
//        setContentView(view)
        setContentView(R.layout.activity_home)
    }
}