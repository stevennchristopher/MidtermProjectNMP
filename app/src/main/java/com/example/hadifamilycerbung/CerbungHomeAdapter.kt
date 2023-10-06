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
        return Global.cerbung.size
    }

    override fun onBindViewHolder(holder: CerbungViewHolder, position: Int) {
        val url = Global.cerbung[position].url
        val builder = Picasso.Builder(holder.itemView.context)
        builder.listener{picasso, url, exception -> exception.printStackTrace()}

        with(holder.binding){
            Picasso.get().load(url).into(imgCerbungCardHome)
            txtTitleCardHome.text = Global.cerbung[position].title
            txtUsernameCardHome.text = "by " + Global.userData[Global.cerbung[position].userId-1].username
            txtNumberCardHome.text = Global.cerbung[position].number.toString()
            txtThumbsCardHome.text = Global.cerbung[position].thumbs.toString()
            txtDescCardHome.text = Global.cerbung[position].description
            btnReadCardHome.setOnClickListener{

            }
        }
    }
}