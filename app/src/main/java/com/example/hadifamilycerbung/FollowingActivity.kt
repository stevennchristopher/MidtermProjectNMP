package com.example.hadifamilycerbung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hadifamilycerbung.databinding.ActivityFollowingBinding
import com.example.hadifamilycerbung.databinding.ActivityHomeBinding

class FollowingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFollowingBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityFollowingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}