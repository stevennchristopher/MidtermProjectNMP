package com.example.hadifamilycerbung

import android.R
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hadifamilycerbung.databinding.CardHomeItemBinding
import com.example.hadifamilycerbung.databinding.CerbungItemFollowingBinding
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FollowingAdapter(private val cerbungs:ArrayList<Cerbung>, private val userId: Int):RecyclerView.Adapter<FollowingAdapter.CerbungViewHolder>() {
    class CerbungViewHolder(val binding: CerbungItemFollowingBinding):RecyclerView.ViewHolder(binding.root)

    companion object {
        val id_cerbungHadiFamily = "idcerbung_random_1928391823"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CerbungViewHolder {
        val binding = CerbungItemFollowingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CerbungViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cerbungs.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CerbungViewHolder, position: Int) {
        val currentCerbung = cerbungs[position]

        with(holder.binding) {
            Picasso.get()
                .load(currentCerbung.urlPhoto)
                .into(imgCerbungFollowing)

            txtCerbungTitle.text = currentCerbung.title

            val q = Volley.newRequestQueue(holder.itemView.context)
            val url = "https://ubaya.me/native/160721046/project/get_cerbung_detail1.php"

            val stringRequest = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener { response ->
                    try {
                        val jsonResponse = JSONObject(response)
                        if (jsonResponse.getString("result") == "OK") {
                            val author = "by " + jsonResponse.getString("author")

                            txtUsernameFollowingCerbung.text = author
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { error ->
                    Log.d("Error", error.message.toString())
                }
            ) {
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["id"] = currentCerbung.id.toString()
                    return params
                }
            }
            q.add(stringRequest)

            val lastUpdate = LocalDateTime.parse(currentCerbung.createDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

            txtLastUpdated.text = "Last updated: " + lastUpdate.format(outputFormatter)

            btnReadCerbung.setOnClickListener {
                val context = holder.itemView.context
                val intent = Intent(context, ReadActivity::class.java)
                intent.putExtra(id_cerbungHadiFamily, currentCerbung.id)
                intent.putExtra(ReadActivity.user_login_cerbungHadiFamily, userId)
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

