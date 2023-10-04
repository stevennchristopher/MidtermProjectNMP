package com.example.hadifamilycerbung

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hadifamilycerbung.databinding.ActivityCardHomeBinding
import com.squareup.picasso.Picasso

class CerbungHomeAdapter():RecyclerView.Adapter<CerbungHomeAdapter.CerbungViewHolder>() {
    class CerbungViewHolder(val binding: ActivityCardHomeBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CerbungViewHolder {
        val binding = ActivityCardHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CerbungViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return Global.cerbungHome.size
    }

    override fun onBindViewHolder(holder: CerbungViewHolder, position: Int) {
        val url = Global.cerbungHome[position].url
        val builder = Picasso.Builder(holder.itemView.context)
        builder.listener{picasso, url, exception -> exception.printStackTrace()}

        with(holder.binding){
            Picasso.get().load(url).into(imgCerbungCardHome)
            txtTitleCardHome.text = Global.cerbungHome[position].title
            txtUsernameCardHome.text = Global.cerbungHome[position].usernameCreate
            txtNumberCardHome.text = Global.cerbungHome[position].number.toString()
            txtThumbsCardHome.text = Global.cerbungHome[position].thumbs.toString()
            txtDescCardHome.text = Global.cerbungHome[position].description
            btnReadCardHome.setOnClickListener{

            }
        }
    }
}