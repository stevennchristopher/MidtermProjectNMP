package com.example.hadifamilycerbung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.example.hadifamilycerbung.databinding.ActivityCreate1Binding
import com.example.hadifamilycerbung.databinding.ActivityCreate2Binding
import com.example.hadifamilycerbung.databinding.ActivityHomeBinding
import com.google.android.material.appbar.MaterialToolbar

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

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        val titleTextView = toolbar.findViewById<TextView>(R.id.title)
        titleTextView.text = "Create Cerbung"

        val title = intent.getStringExtra(title_cerbungHadiFamily)
        val description = intent.getStringExtra(description_cerbungHadiFamily)
        val url = intent.getStringExtra(imgUrl_cerbungHadiFamily)
        val genre = intent.getIntExtra(genre_cerbungHadiFamily, 0)
        val userId = intent.getIntExtra(Create1Activity.user_login_cerbungHadiFamily, 0)
        val access = intent.getStringExtra(access_cerbungHadiFamily)
        val paragraph = intent.getStringExtra(paragraph_cerbungHadiFamily)
        val rulesCheck = intent.getStringExtra(rules_agree_cerbungHadiFamily)

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
            if (binding.txtInputEditParagraph.text.toString().trim().isEmpty()){
                Toast.makeText(this, "You must write a paragraph first", Toast.LENGTH_SHORT).show()
            }
            else{
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
                finish()
            }
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
            finish()
        }

        binding.bottomNav.selectedItemId = R.id.itemCreate
        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.itemHome -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra(HomeActivity.user_login_cerbungHadiFamily, userId)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.itemFollowing -> {
                    val intent = Intent(this, FollowingActivity::class.java)
                    intent.putExtra(FollowingActivity.user_login_cerbungHadiFamily, userId)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.itemCreate -> {
                    val intent = Intent(this, Create1Activity::class.java)
                    intent.putExtra(Create1Activity.user_login_cerbungHadiFamily, userId)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.itemUser -> {
                    val intent = Intent(this, Users1Activity::class.java)
                    intent.putExtra(Users1Activity.user_login_cerbungHadiFamily, userId)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.itemPref -> {
                    val intent = Intent(this, PrefsActivity::class.java)
                    intent.putExtra(Create1Activity.user_login_cerbungHadiFamily, userId)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }

        binding.customToolbar.notifIcon.setOnClickListener{
            val intent = Intent(this, NotificationsActivity::class.java)
            intent.putExtra(NotificationsActivity.user_login_cerbungHadiFamily, userId)
            startActivity(intent)
            finish()
            true
        }
    }
}