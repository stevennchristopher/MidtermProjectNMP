package com.example.hadifamilycerbung

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hadifamilycerbung.databinding.ParagraphBinding
import org.json.JSONException
import org.json.JSONObject

class ParagraphAdapter(val paragraphs:ArrayList<Paragraph>, val userId: Int): RecyclerView.Adapter<ParagraphAdapter.ParagraphViewHolder>() {

    class ParagraphViewHolder(val binding: ParagraphBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParagraphViewHolder {
        val binding = ParagraphBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParagraphViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return paragraphs.size
    }

    override fun onBindViewHolder(holder: ParagraphViewHolder, position: Int) {
        var isLiked = false
        val iconLiked = R.drawable.likes
        val iconNotLiked = R.drawable.like

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

                            txtInputTitle.text = author //gabisa diubah jadi txtAuthor namae di layout mungkin karena copas layout lain
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

            val q4 = Volley.newRequestQueue(holder.itemView.context)
            val url4 = "https://ubaya.me/native/160721046/project/cek_like_paragraph.php"

            val stringRequest4 = object : StringRequest(
                Request.Method.POST, url4,
                { response ->
                    Log.d("apiresult", response)

                    try {
                        val jsonResponse = JSONObject(response)
                        val result = jsonResponse.getString("result")

                        if (result == "Sudah like") {
                            btnLikeParagraph.setBackgroundResource(iconLiked)
                            isLiked = true
                        } else {
                            btnLikeParagraph.setBackgroundResource(iconNotLiked)
                            isLiked = false
                        }
                    } catch (e: JSONException) {
                        Log.e("apiresult", "error di try catch: " + e.message)
                    }
                },
                { error ->
                    Log.e("apiresult", error.message.toString())
                })
            {
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["userId"] = userId.toString()
                    params["paragraphId"] = paragraphs[position].id.toString()
                    return params
                }
            }
            q4.add(stringRequest4)

            btnLikeParagraph.setOnClickListener(){
                if (isLiked){
                    val q3 = Volley.newRequestQueue(holder.itemView.context)
                    val url3 = "https://ubaya.me/native/160721046/project/unlike_paragraph.php"

                    val stringRequest3 = object : StringRequest(
                        Request.Method.POST, url3,
                        { response ->
                            Log.d("apiresult", response)

                            try {
                                val jsonResponse = JSONObject(response)
                                val result = jsonResponse.getString("result")

                                if (result == "OK") {
                                    btnLikeParagraph.setBackgroundResource(iconNotLiked)
                                    isLiked = false
                                } else {
                                }
                            } catch (e: JSONException) {
                                Log.e("apiresult", "error di try catch: " + e.message)
                            }
                        },
                        { error ->
                            Log.e("apiresult", error.message.toString())
                        })
                    {
                        override fun getParams(): Map<String, String> {
                            val params = HashMap<String, String>()
                            params["userId"] = userId.toString()
                            params["paragraphId"] = paragraphs[position].id.toString()
                            return params
                        }
                    }
                    q3.add(stringRequest3)
                }else{
                    val q3 = Volley.newRequestQueue(holder.itemView.context)
                    val url3 = "https://ubaya.me/native/160721046/project/like_paragraph.php"

                    val stringRequest3 = object : StringRequest(
                        Request.Method.POST, url3,
                        { response ->
                            Log.d("apiresult", response)

                            try {
                                val jsonResponse = JSONObject(response)
                                val result = jsonResponse.getString("result")

                                if (result == "OK") {
                                    btnLikeParagraph.setBackgroundResource(iconLiked)
                                    isLiked = true
                                } else {
                                }
                            } catch (e: JSONException) {
                                Log.e("apiresult", "error di try catch: " + e.message)
                            }
                        },
                        { error ->
                            Log.e("apiresult", error.message.toString())
                        })
                    {
                        override fun getParams(): Map<String, String> {
                            val params = HashMap<String, String>()
                            params["userId"] = userId.toString()
                            params["paragraphId"] = paragraphs[position].id.toString()
                            return params
                        }
                    }
                    q3.add(stringRequest3)
                }
            }
        }


    }

    fun updateData(newParagraph: List<Paragraph>) {
        paragraphs.clear()
        paragraphs.addAll(newParagraph)
        notifyDataSetChanged()
    }


}