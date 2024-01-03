package com.example.hadifamilycerbung

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hadifamilycerbung.databinding.ActivityHomeBinding
import com.example.hadifamilycerbung.databinding.ActivityNotificationsBinding
import com.google.android.material.appbar.MaterialToolbar

class NotificationsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationsBinding

    companion object {
        val user_login_cerbungHadiFamily = "random_16071239872_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityNotificationsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.customToolbar.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.customToolbar.title.text = "Notification"

        val userId = intent.getIntExtra(user_login_cerbungHadiFamily, 0)
        Log.d("apiresult", userId.toString())

        val lm: LinearLayoutManager = LinearLayoutManager(this)
        binding.recycleViewNotification.layoutManager = lm
        binding.recycleViewNotification.setHasFixedSize(true)
        binding.recycleViewNotification.adapter = NotificationAdapter()
    }
}