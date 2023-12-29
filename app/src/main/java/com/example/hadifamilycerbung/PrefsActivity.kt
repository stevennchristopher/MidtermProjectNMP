package com.example.hadifamilycerbung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
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
        val titleTextView = toolbar.findViewById<TextView>(R.id.title)
        titleTextView.text = "Prefs"

        binding.btnLogOut.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        val darkModeSwitch = findViewById<Switch>(R.id.btnDarkMode)

        darkModeSwitch.isChecked = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES

        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Set the appropriate mode based on isChecked value
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        binding.bottomNav.selectedItemId = R.id.itemPref
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