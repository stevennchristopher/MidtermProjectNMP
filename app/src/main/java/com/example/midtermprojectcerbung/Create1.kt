package com.example.midtermprojectcerbung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.midtermprojectcerbung.databinding.ActivityCreate1Binding
import com.example.midtermprojectcerbung.databinding.ActivityHomeBinding

class Create1 : AppCompatActivity() {
    private lateinit var binding: ActivityCreate1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreate1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val items = listOf("Aksi", "Horror", "Misteri","Kocak")
        val adapter = ArrayAdapter(this, R.layout.activity_create1, items)
        binding.txtInputEditGenre.setAdapter(adapter)
    }
}