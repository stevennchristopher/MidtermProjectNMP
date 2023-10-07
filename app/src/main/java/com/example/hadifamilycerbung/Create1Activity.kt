package com.example.hadifamilycerbung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
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

        val rules_agree_cerbungHadiFamily = "rules_ryanryanryan10101010"
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
        val userId = intent.getIntExtra(user_login_cerbungHadiFamily, 0)
        val access = intent.getStringExtra(access_cerbungHadiFamily)
        val paragraph = intent.getStringExtra(paragraph_cerbungHadiFamily)
        val rulesCheck = intent.getStringExtra(rules_agree_cerbungHadiFamily)

        binding.txtInputTitle.setText(title)
        binding.txtInputDescription.setText(description)
        binding.txtInputImg.setText(imgUrl)

        binding.autoCompleteTxtInputImg.setText(genre, false)

        binding.btnNextC1.setOnClickListener{
            if (binding.txtInputTitle.text.toString().trim().isEmpty()){
                binding.txtInputTitle.error = "Title cannot be empty"
            }
            else if(binding.txtInputDescription.text.toString().trim().isEmpty()){
                binding.txtInputDescription.error = "Description cannot be empty"
            }
            else if(binding.txtInputImg.text.toString().trim().isEmpty()){
                binding.txtInputImg.error = "Image URL cannot be empty"
            }
            else if(binding.autoCompleteTxtInputImg.text.trim().isEmpty()){
                Toast.makeText(this, "Choose a genre!", Toast.LENGTH_SHORT).show()
            }
            else{
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
                intent.putExtra(Create3Activity.rules_agree_cerbungHadiFamily, rulesCheck)
                startActivity(intent)
                finish()
            }
        }

        binding.btnHome.setOnClickListener{
            finish()
        }


    }
}