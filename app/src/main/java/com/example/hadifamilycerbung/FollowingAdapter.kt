package com.example.hadifamilycerbung

import android.R
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hadifamilycerbung.databinding.CardHomeItemBinding
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject

class FollowingAdapter(private val cerbungs:ArrayList<Cerbung>):RecyclerView.Adapter<FollowingAdapter.CerbungViewHolder>() {
    class CerbungViewHolder(val binding: CardHomeItemBinding):RecyclerView.ViewHolder(binding.root)

    companion object {
        val id_cerbungHadiFamily = "idcerbung_random_1928391823"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CerbungViewHolder {
        val binding = CardHomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CerbungViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cerbungs.size
    }

    override fun onBindViewHolder(holder: CerbungViewHolder, position: Int) {
        val currentCerbung = cerbungs[position]

        with(holder.binding) {
            Picasso.get()
                .load(currentCerbung.urlPhoto)
                .into(imgCerbungCardHome)

            txtTitleCardHome.text = currentCerbung.title
            txtDescCardHome.text = currentCerbung.description
            btnReadCardHome.setOnClickListener {
                val context = holder.itemView.context
                val intent = Intent(context, ReadActivity::class.java)
                intent.putExtra(id_cerbungHadiFamily, currentCerbung.id)
                context.startActivity(intent)
            }
        }
    }

    fun updateData(newCerbungs: List<Cerbung>) {
        cerbungs.clear()
        cerbungs.addAll(newCerbungs)
        notifyDataSetChanged()
    }
}

