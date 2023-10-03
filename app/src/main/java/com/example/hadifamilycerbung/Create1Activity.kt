package com.example.hadifamilycerbung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.hadifamilycerbung.databinding.ActivityCreate1Binding
import com.example.hadifamilycerbung.databinding.ActivityHomeBinding

class Create1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCreate1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreate1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val genres = resources.getStringArray(R.array.genres)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_genre, genres)
        binding.autoCompleteTxtInputImg.setAdapter(arrayAdapter)


    }
}