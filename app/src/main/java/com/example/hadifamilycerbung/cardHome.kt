package com.example.hadifamilycerbung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hadifamilycerbung.databinding.ActivityCardHomeBinding

class cardHome : AppCompatActivity() {
    private lateinit var binding: ActivityCardHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}