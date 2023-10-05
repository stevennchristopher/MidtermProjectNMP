package com.example.hadifamilycerbung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hadifamilycerbung.databinding.ActivityHomeBinding
import com.example.hadifamilycerbung.databinding.ActivityReadPublicBinding

class ReadPublicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReadPublicBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityReadPublicBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}