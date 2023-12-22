package com.example.hadifamilycerbung

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hadifamilycerbung.databinding.CardHomeItemBinding
import com.squareup.picasso.Picasso
import org.json.JSONObject

class CerbungAdapter(private val cerbungs:ArrayList<Cerbung>):RecyclerView.Adapter<CerbungAdapter.CerbungViewHolder>() {
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
        val q = Volley.newRequestQueue(holder.itemView.context)
        val url = "https://ubaya.me/native/160721046/project/get_cerbung.php"

        var idCerbung = ""
        var titleCerbung = ""
        var urlPhotoCerbung = ""
        var descriptionCerbung = ""

        var stringRequest = object : StringRequest(
            Request.Method.POST, url,
            {
                Log.d("apiresult", it)
                var obj = JSONObject(it)

                idCerbung = obj.getString("id")
                titleCerbung = obj.getString("title")
                urlPhotoCerbung = obj.getString("urlPhoto")
                descriptionCerbung = obj.getString("description")
            },
            Response.ErrorListener {
                Log.e("apiresult", it.message.toString())
            })
        {}
        q.add(stringRequest)


        val builder = Picasso.Builder(holder.itemView.context)
        builder.listener{picasso, urlPhotoCerbung, exception -> exception.printStackTrace()}

        with(holder.binding){
            Picasso.get().load(urlPhotoCerbung).into(imgCerbungCardHome)
            txtTitleCardHome.text = titleCerbung
//            txtUsernameCardHome.text = "by " + Global.userData[Global.cerbung[position].userId-1].username
//            txtNumberCardHome.text = Global.cerbung[position].number.toString()
//            txtThumbsCardHome.text = Global.cerbung[position].thumbs.toString()
            txtDescCardHome.text = descriptionCerbung

            btnReadCardHome.setOnClickListener{
                val context = holder.itemView.context
                val intent = Intent(context, ReadActivity::class.java)
                intent.putExtra(id_cerbungHadiFamily, idCerbung)
                context.startActivity(intent)
            }
        }
    }
}