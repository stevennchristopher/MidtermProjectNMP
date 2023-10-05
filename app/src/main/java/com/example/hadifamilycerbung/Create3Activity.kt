package com.example.hadifamilycerbung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hadifamilycerbung.databinding.ActivityCreate2Binding
import com.example.hadifamilycerbung.databinding.ActivityCreate3Binding
import com.example.hadifamilycerbung.databinding.ActivityHomeBinding

class Create3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCreate3Binding
    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityCreate3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}