package com.example.hadifamilycerbung;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hadifamilycerbung.databinding.ReadItemBinding;
import com.squareup.picasso.Picasso

class ReadAdapter() : RecyclerView.Adapter<ReadAdapter.ReadViewHolder>() {
    class ReadViewHolder(val binding:
                         ReadItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadViewHolder {
        val binding = ReadItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return ReadViewHolder(binding)
    }

    override fun getItemCount(): Int {
    return Global.paragraph.size
    }

    override fun onBindViewHolder(holder: ReadViewHolder, position: Int) {
        with(holder.binding)
        {
            val cerbungId = Global.paragraph[position].cerbungId
            val userId = Global.paragraph[position].userId
            val builder = Picasso.Builder(holder.itemView.context)
            builder.listener { picasso, uri, exception -> exception.printStackTrace() }

        }


    }
}
