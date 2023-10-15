package com.example.hadifamilycerbung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hadifamilycerbung.databinding.ActivityCreate2Binding
import com.example.hadifamilycerbung.databinding.ActivityCreate3Binding
import com.example.hadifamilycerbung.databinding.ActivityHomeBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.Locale

class Create3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCreate3Binding

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
        binding = ActivityCreate3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val title = intent.getStringExtra(title_cerbungHadiFamily)
        val description = intent.getStringExtra(description_cerbungHadiFamily)
        val url = intent.getStringExtra(imgUrl_cerbungHadiFamily)
        val genre = intent.getStringExtra(genre_cerbungHadiFamily)
        val userId = intent.getIntExtra(Create1Activity.user_login_cerbungHadiFamily, 0)
        val access = intent.getStringExtra(access_cerbungHadiFamily)
        val paragraph = intent.getStringExtra(paragraph_cerbungHadiFamily)
        val rulesCheck = intent.getStringExtra(Create1Activity.rules_agree_cerbungHadiFamily)

        binding.txtTitleCerbung.text = title
        binding.txtDescriptionCerbung.text = description
        binding.txtDescriptionCerbung2.text = paragraph
        binding.btnDisplayAccess.text = access
        binding.btnDisplayGenre.text = genre

        if (rulesCheck == "yes"){
            binding.checkBoxTerms.isChecked = true
        }
        else {
            binding.checkBoxTerms.isChecked = false
        }

        binding.btnPublish.setOnClickListener{
            if (binding.checkBoxTerms.isChecked){

                var countCerbung = 0
                for (Cerbung in Global.cerbung){
                    countCerbung++
                }

                var countParagraph = 0
                for (Cerbung in Global.paragraph){
                    countParagraph++
                }

                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH) + 1
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val currentDate = "$day/$month/$year"

                val newCerbung = Cerbung((countCerbung+1),url.toString(),title.toString(), userId.toString().toInt(),1,0,description.toString(), genre.toString(), access.toString(), SimpleDateFormat("dd/MM/yyyy").parse(currentDate))
                Global.cerbung.add(newCerbung)
                val newParagraph = Paragraph((countParagraph+1),(countCerbung+1), userId.toString().toInt(),paragraph.toString())
                Global.paragraph.add(newParagraph)
                Toast.makeText(this, "Publish successful", Toast.LENGTH_LONG).show()
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra(user_login_cerbungHadiFamily, userId)
                finishAffinity()
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Please agree to our terms before publishing", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnPrevC3.setOnClickListener{
            val intent = Intent(this, Create2Activity::class.java)
            intent.putExtra(title_cerbungHadiFamily, title)
            intent.putExtra(description_cerbungHadiFamily, description)
            intent.putExtra(imgUrl_cerbungHadiFamily, url)
            intent.putExtra(genre_cerbungHadiFamily, genre)
            intent.putExtra(user_login_cerbungHadiFamily, userId)
            intent.putExtra(access_cerbungHadiFamily, access)
            intent.putExtra(paragraph_cerbungHadiFamily, paragraph)

            var rulesCheck = "no"
            if (binding.checkBoxTerms.isChecked){
                rulesCheck = "yes"
            }

            intent.putExtra(rules_agree_cerbungHadiFamily, rulesCheck)

            startActivity(intent)
            finish()
        }

        binding.btnHome.setOnClickListener{
            finish()
        }
    }
}