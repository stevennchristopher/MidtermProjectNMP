package com.example.hadifamilycerbung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hadifamilycerbung.databinding.ActivityHomeBinding
import com.example.hadifamilycerbung.databinding.ActivityReadRestrictBinding

class ReadRestrictActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReadRestrictBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityReadRestrictBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}