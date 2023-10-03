package com.example.hadifamilycerbung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hadifamilycerbung.databinding.ActivityHomeBinding
import com.example.hadifamilycerbung.databinding.ActivityUsers1Binding

class Users1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityUsers1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsers1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}