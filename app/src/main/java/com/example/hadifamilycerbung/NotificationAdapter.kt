package com.example.hadifamilycerbung

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hadifamilycerbung.databinding.ActivityCardHomeBinding
import com.example.hadifamilycerbung.databinding.NotificationItemBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.Date
import java.util.Locale

class NotificationAdapter(): RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>()  {
    class NotificationViewHolder(val binding: NotificationItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding = NotificationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationAdapter.NotificationViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return Global.cerbungNotification.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        with(holder.binding){
            val date = Global.cerbungNotification[position].date
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            val formattedDate = dateFormat.format(date)

            txtTanggal.text = formattedDate
            txtDescription.text = Global.cerbungNotification[position].user + " " + Global.cerbungNotification[position].description
            btnRespond2.text = Global.cerbungNotification[position].type
        }
    }
}