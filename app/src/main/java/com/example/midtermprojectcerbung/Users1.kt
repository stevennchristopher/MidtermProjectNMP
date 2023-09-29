package com.example.midtermprojectcerbung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.midtermprojectcerbung.databinding.ActivityNotificationsBinding
import com.example.midtermprojectcerbung.databinding.ActivityUsers1Binding

class Users1 : AppCompatActivity() {

    private lateinit var binding: ActivityUsers1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsers1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //setContentView(R.layout.activity_users1)
    }
}