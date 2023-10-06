package com.example.hadifamilycerbung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import com.example.hadifamilycerbung.databinding.ActivityCreate1Binding
import com.example.hadifamilycerbung.databinding.ActivityHomeBinding

class Create1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCreate1Binding

    companion object {
        val title_cerbungHadiFamily = "title_random_1928391823"
        val description_cerbungHadiFamily = "description_random_1928391823"
        val imgUrl_cerbungHadiFamily = "url_random_1928391823"
        val genre_cerbungHadiFamily = "genre_random_1928391823"
        val user_login_cerbungHadiFamily = "random_16071239872_user"

        val access_cerbungHadiFamily = "access_19283918231607210"
        val paragraph_cerbungHadiFamily = "firstpara_19283918231607210"
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

        val title = intent.getStringExtra(title_cerbungHadiFamily)
        val description = intent.getStringExtra(description_cerbungHadiFamily)
        val imgUrl = intent.getStringExtra(imgUrl_cerbungHadiFamily)
        val genre = intent.getStringExtra(genre_cerbungHadiFamily)
        val userId = intent.getStringExtra(user_login_cerbungHadiFamily)
        val access = intent.getStringExtra(access_cerbungHadiFamily)
        val paragraph = intent.getStringExtra(paragraph_cerbungHadiFamily)

        binding.txtInputTitle.setText(title)
        binding.txtInputDescription.setText(description)
        binding.txtInputImg.setText(imgUrl)

        binding.autoCompleteTxtInputImg.setText(genre, false)

        binding.btnNextC1.setOnClickListener{
            val title = binding.txtInputTitle.text.toString()
            val description = binding.txtInputDescription.text.toString()
            val imgCoverUrl = binding.txtInputImg.text.toString()
            val genre = binding.autoCompleteTxtInputImg.text.toString()

            val intent = Intent(this, Create2Activity::class.java)
            intent.putExtra(title_cerbungHadiFamily, title)
            intent.putExtra(description_cerbungHadiFamily, description)
            intent.putExtra(imgUrl_cerbungHadiFamily, imgCoverUrl)
            intent.putExtra(genre_cerbungHadiFamily, genre)
            intent.putExtra(user_login_cerbungHadiFamily, userId)
            intent.putExtra(access_cerbungHadiFamily, access)
            intent.putExtra(paragraph_cerbungHadiFamily, paragraph)
            startActivity(intent)
        }


    }
}