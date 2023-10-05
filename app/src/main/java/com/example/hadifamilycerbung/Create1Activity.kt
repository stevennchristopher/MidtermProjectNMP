package com.example.hadifamilycerbung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.hadifamilycerbung.databinding.ActivityCreate1Binding
import com.example.hadifamilycerbung.databinding.ActivityHomeBinding

class Create1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCreate1Binding

    companion object {
        val title_cerbung = "title"
        val description_cerbung = "description"
        val imgUrl_cerbung = "url"
        val genre_cerbung = "genre"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityCreate1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val genres = resources.getStringArray(R.array.genres)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_genre, genres)
        binding.autoCompleteTxtInputImg.setAdapter(arrayAdapter)

        binding.btnNextC1.setOnClickListener{
            val title = binding.txtInputTitle.text.toString()
            val description = binding.txtInputDescription.text.toString()
            val imgCoverUrl = binding.txtInputImg.text.toString()
            val genre = binding.autoCompleteTxtInputImg.text.toString()

            intent.putExtra(Create1Activity.title_cerbung, title)
            intent.putExtra(Create1Activity.description_cerbung, description)
            intent.putExtra(Create1Activity.imgUrl_cerbung, imgCoverUrl)
            intent.putExtra(Create1Activity.genre_cerbung, genre)
            val intent = Intent(this, Create2Activity::class.java)
            startActivity(intent)
        }


    }
}