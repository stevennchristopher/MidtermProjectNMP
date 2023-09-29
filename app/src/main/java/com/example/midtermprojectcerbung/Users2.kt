package com.example.midtermprojectcerbung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.midtermprojectcerbung.databinding.ActivityNotificationsBinding
import com.example.midtermprojectcerbung.databinding.ActivityUsers2Binding

class Users2 : AppCompatActivity() {

    private lateinit var binding: ActivityUsers2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsers2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //setContentView(R.layout.activity_users2)
    }
}