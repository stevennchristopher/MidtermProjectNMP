package com.example.hadifamilycerbung

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hadifamilycerbung.databinding.ActivityCardHomeBinding
import com.squareup.picasso.Picasso

class CerbungHomeAdapter(private val userId:Int):RecyclerView.Adapter<CerbungHomeAdapter.CerbungViewHolder>() {
    class CerbungViewHolder(val binding: ActivityCardHomeBinding):RecyclerView.ViewHolder(binding.root)

    companion object {
        val id_cerbungHadiFamily = "idcerbung_random_1928391823"
        val user_login_cerbungHadiFamily = "random_16071239872_user"
    }

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
                val context = holder.itemView.context
                val intent = Intent(context, ReadActivity::class.java)
                intent.putExtra(id_cerbungHadiFamily, Global.cerbung[position].id)
                context.startActivity(intent)
//                (context as Activity).finish()
            }

        }
    }
}