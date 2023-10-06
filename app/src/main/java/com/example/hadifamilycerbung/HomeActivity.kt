package com.example.hadifamilycerbung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hadifamilycerbung.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    companion object {
        val user_login_cerbungHadiFamily = "random_16071239872_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val user = intent.getStringExtra(user_login_cerbungHadiFamily)

        val lm: LinearLayoutManager = LinearLayoutManager(this)
        binding.recycleViewHome.layoutManager = lm
        binding.recycleViewHome.setHasFixedSize(true)
        binding.recycleViewHome.adapter = CerbungHomeAdapter()

        binding.btnNotif.setOnClickListener{
            val intent = Intent(this, NotificationsActivity::class.java)
            startActivity(intent)
        }

        binding.btnFollowing.setOnClickListener{
            val intent = Intent(this, FollowingActivity::class.java)
            startActivity(intent)
        }

        binding.btnCreate.setOnClickListener{
            val intent = Intent(this, Create1Activity::class.java)
            intent.putExtra(user_login_cerbungHadiFamily, user)
            startActivity(intent)
        }

        binding.btnUser.setOnClickListener{
            val intent = Intent(this, Users1Activity::class.java)
            startActivity(intent)
        }

        binding.btnPrefs.setOnClickListener{
            val intent = Intent(this, PrefsActivity::class.java)
            startActivity(intent)
        }
    }
}