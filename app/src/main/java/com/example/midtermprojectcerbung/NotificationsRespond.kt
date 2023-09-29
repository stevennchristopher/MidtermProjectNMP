package com.example.midtermprojectcerbung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.midtermprojectcerbung.databinding.ActivityNotificationsBinding
import com.example.midtermprojectcerbung.databinding.ActivityNotificationsRespondBinding

class NotificationsRespond : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationsRespondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationsRespondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //setContentView(R.layout.activity_notifications_respond)
    }
}