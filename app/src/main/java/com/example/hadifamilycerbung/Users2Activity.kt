package com.example.hadifamilycerbung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hadifamilycerbung.databinding.ActivityHomeBinding
import com.example.hadifamilycerbung.databinding.ActivityUsers2Binding

class Users2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityUsers2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsers2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}