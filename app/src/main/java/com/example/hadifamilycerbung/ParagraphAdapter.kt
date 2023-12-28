package com.example.hadifamilycerbung

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hadifamilycerbung.databinding.ParagraphBinding
import org.json.JSONException
import org.json.JSONObject

class ParagraphAdapter(val paragraphs:ArrayList<Paragraph>): RecyclerView.Adapter<ParagraphAdapter.ParagraphViewHolder>() {

    class ParagraphViewHolder(val binding: ParagraphBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParagraphViewHolder {
        val binding = ParagraphBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParagraphViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return paragraphs.size
    }

    override fun onBindViewHolder(holder: ParagraphViewHolder, position: Int) {
        val currentParagraph = paragraphs[position]
        with(holder.binding){
            txtPara.text = currentParagraph.content

            val q = Volley.newRequestQueue(holder.itemView.context)
            val url = "https://ubaya.me/native/160721046/project/get_username.php"

            val stringRequest = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener { response ->
                    try {
                        val jsonResponse = JSONObject(response)
                        if (jsonResponse.getString("result") == "OK") {
                            val author = jsonResponse.getString("uname")

                            txtInputTitle.text = author //gk tau kenapa gabisa diubah jadi txtAuthor namae di layout mungkin karena copas layout lain
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
                    params["id"] = currentParagraph.userId.toString()
                    return params
                }
            }
            q.add(stringRequest)
        }
    }

    fun updateData(newParagraph: List<Paragraph>) {
        paragraphs.clear()
        paragraphs.addAll(newParagraph)
        notifyDataSetChanged()
    }


}