package com.example.hadifamilycerbung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hadifamilycerbung.databinding.ActivityHomeBinding
import com.example.hadifamilycerbung.databinding.ActivityUsersProfileBinding

class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUsersProfileBinding
    companion object {
        val user_login_cerbungHadiFamily = "random_16071239872_user"
        val id_userSelectedHadiFamily = "idcerbung_random_1928391823"
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityUsersProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}