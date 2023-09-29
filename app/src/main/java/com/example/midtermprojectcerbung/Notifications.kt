package com.example.midtermprojectcerbung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.midtermprojectcerbung.databinding.ActivityHomeBinding
import com.example.midtermprojectcerbung.databinding.ActivityNotificationsBinding

class Notifications : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //setContentView(R.layout.activity_notifications)
    }
}