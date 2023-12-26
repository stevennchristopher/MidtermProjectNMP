package com.example.hadifamilycerbung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.hadifamilycerbung.databinding.ActivityHomeBinding
import com.example.hadifamilycerbung.databinding.ActivityPrefsBinding
import com.google.android.material.appbar.MaterialToolbar

class PrefsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrefsBinding
    companion object {
        val user_login_cerbungHadiFamily = "random_16071239872_user"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrefsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val userId = intent.getIntExtra(HomeActivity.user_login_cerbungHadiFamily, 0)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)

        // Find the TextView inside the toolbar and set the title
        val titleTextView = toolbar.findViewById<TextView>(R.id.title)
        titleTextView.text = "Prefs"

        binding.bottomNav.selectedItemId = R.id.itemPref
        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.itemHome -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra(HomeActivity.user_login_cerbungHadiFamily, userId)
                    startActivity(intent)
                    true
                }
                R.id.itemFollowing -> {
                    val intent = Intent(this, FollowingActivity::class.java)
                    intent.putExtra(FollowingActivity.user_login_cerbungHadiFamily, userId)
                    startActivity(intent)
                    true
                }
                R.id.itemCreate -> {
                    val intent = Intent(this, Create1Activity::class.java)
                    intent.putExtra(Create1Activity.user_login_cerbungHadiFamily, userId)
                    startActivity(intent)
                    true
                }
                R.id.itemUser -> {
                    val intent = Intent(this, Users1Activity::class.java)
                    intent.putExtra(Users1Activity.user_login_cerbungHadiFamily, userId)
                    startActivity(intent)
                    true
                }
                R.id.itemPref -> {
                    true
                }
                else -> false
            }
        }
    }
    override fun onBackPressed() {
        binding.bottomNav.selectedItemId = R.id.itemHome
    }
}