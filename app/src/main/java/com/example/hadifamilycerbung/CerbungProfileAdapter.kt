package com.example.hadifamilycerbung

import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.hadifamilycerbung.databinding.CerbungItemProfileBinding
import com.example.hadifamilycerbung.databinding.UsersItemBinding
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CerbungProfileAdapter (private val cerbungs:ArrayList<CerbungProfile>): RecyclerView.Adapter<CerbungProfileAdapter.CerbungProfileViewHolder>() {
    class CerbungProfileViewHolder(val binding: CerbungItemProfileBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CerbungProfileViewHolder {
        val binding = CerbungItemProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CerbungProfileViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cerbungs.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CerbungProfileAdapter.CerbungProfileViewHolder, position: Int) {
        val currentCerbungs = cerbungs[position]

        with(holder.binding) {
            Picasso.get()
                .load(currentCerbungs.urlPhoto)
                .into(imgBgcerbungItemProf)

            txtCerbungTitle.text = currentCerbungs.title

            val lastUpdate = LocalDateTime.parse(currentCerbungs.lastUpdate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            txtLastUpdated.text = "Last update: " + lastUpdate.format(outputFormatter)

            txtLikes.text = currentCerbungs.totalLikes.toString()

        }
    }

    fun updateData(newCerbungs: List<CerbungProfile>) {
        cerbungs.clear()
        cerbungs.addAll(newCerbungs)
        notifyDataSetChanged()
    }
}