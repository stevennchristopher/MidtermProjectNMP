package com.example.hadifamilycerbung

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hadifamilycerbung.databinding.ActivityHomeBinding
import com.example.hadifamilycerbung.databinding.ActivityNotificationsBinding

class NotificationsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationsBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityNotificationsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val lm: LinearLayoutManager = LinearLayoutManager(this)
        binding.recycleViewNotification.layoutManager = lm
        binding.recycleViewNotification.setHasFixedSize(true)
        binding.recycleViewNotification.adapter = NotificationAdapter()
    }
}