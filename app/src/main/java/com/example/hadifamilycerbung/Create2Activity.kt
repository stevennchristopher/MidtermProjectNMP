package com.example.hadifamilycerbung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import com.example.hadifamilycerbung.databinding.ActivityCreate1Binding
import com.example.hadifamilycerbung.databinding.ActivityCreate2Binding
import com.example.hadifamilycerbung.databinding.ActivityHomeBinding

class Create2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCreate2Binding

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
        binding = ActivityCreate2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val title = intent.getStringExtra(title_cerbungHadiFamily)
        val description = intent.getStringExtra(description_cerbungHadiFamily)
        val url = intent.getStringExtra(imgUrl_cerbungHadiFamily)
        val genre = intent.getStringExtra(genre_cerbungHadiFamily)
        val userId = intent.getStringExtra(user_login_cerbungHadiFamily)
        val access = intent.getStringExtra(access_cerbungHadiFamily)
        val paragraph = intent.getStringExtra(paragraph_cerbungHadiFamily)
        val rulesCheck = intent.getStringExtra(Create1Activity.rules_agree_cerbungHadiFamily)

        if (access == "Public"){
            binding.radioButtonRestricted.isChecked = false
            binding.radioButtonPublic.isChecked = true
        }
        else {
            binding.radioButtonPublic.isChecked = false
            binding.radioButtonRestricted.isChecked = true
        }
        binding.txtInputEditParagraph.setText(paragraph)

        binding.btnNextC2.setOnClickListener{
            val intent = Intent(this, Create3Activity::class.java)
            intent.putExtra(title_cerbungHadiFamily, title)
            intent.putExtra(description_cerbungHadiFamily, description)
            intent.putExtra(imgUrl_cerbungHadiFamily, url)
            intent.putExtra(genre_cerbungHadiFamily, genre)
            intent.putExtra(user_login_cerbungHadiFamily, userId)
            intent.putExtra(Create3Activity.rules_agree_cerbungHadiFamily, rulesCheck)

            val selectedRadioButton = findViewById<RadioButton>(binding.radioGroupAccess.checkedRadioButtonId)
            val access = selectedRadioButton.text.toString()

            val paragraph = binding.txtInputEditParagraph.text.toString()

            intent.putExtra(access_cerbungHadiFamily, access)
            intent.putExtra(paragraph_cerbungHadiFamily, paragraph)

            startActivity(intent)
        }

        binding.btnPrevC2.setOnClickListener{
            val intent = Intent(this, Create1Activity::class.java)
            intent.putExtra(title_cerbungHadiFamily, title)
            intent.putExtra(description_cerbungHadiFamily, description)
            intent.putExtra(imgUrl_cerbungHadiFamily, url)
            intent.putExtra(genre_cerbungHadiFamily, genre)
            intent.putExtra(user_login_cerbungHadiFamily, userId)
            intent.putExtra(Create3Activity.rules_agree_cerbungHadiFamily, rulesCheck)

            val selectedRadioButton = findViewById<RadioButton>(binding.radioGroupAccess.checkedRadioButtonId)
            val access = selectedRadioButton.text.toString()

            val paragraph = binding.txtInputEditParagraph.text.toString()

            intent.putExtra(access_cerbungHadiFamily, access)
            intent.putExtra(paragraph_cerbungHadiFamily, paragraph)

            startActivity(intent)
        }

    }
}