package com.example.hadifamilycerbung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hadifamilycerbung.databinding.ActivityReadBinding
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class ReadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReadBinding
    private lateinit var paragraphAdapter: ParagraphAdapter
    private var cerbungId: Int = 0
    private var userId: Int = 0

    var isLiked = false
    val iconLiked = R.drawable.likes
    val iconNotLiked = R.drawable.like

    companion object {
        val id_cerbungHadiFamily = "idcerbung_random_1928391823"
        val user_login_cerbungHadiFamily = "random_16071239872_user"
    }

    var paragraphs : ArrayList<Paragraph> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityReadBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        cerbungId = intent.getIntExtra(id_cerbungHadiFamily, 0)
        userId = intent.getIntExtra(user_login_cerbungHadiFamily, 0)

        refresh()

        val q4 = Volley.newRequestQueue(this)
        val url4 = "https://ubaya.me/native/160721046/project/cek_follow_cerbung.php"

        val stringRequest4 = object : StringRequest(
            Request.Method.POST, url4,
            { response ->
                Log.d("apiresult", response)

                try {
                    val jsonResponse = JSONObject(response)
                    val result = jsonResponse.getString("result")

                    if (result == "Sudah follow") {
                        Log.d("apiresult", "Sudah")
                        binding.btnFollow.text = "Unfollow"
                    } else {
                        Log.d("apiresult", "Belum")
                        binding.btnFollow.text = "Follow"
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
                params["cerbungId"] = cerbungId.toString()
                return params
            }
        }
        q4.add(stringRequest4)



        val lm: LinearLayoutManager = LinearLayoutManager(this)
        binding.recyclerViewParagraphs.layoutManager = lm
        binding.recyclerViewParagraphs.setHasFixedSize(true)
        paragraphAdapter = ParagraphAdapter(paragraphs, userId)
        binding.recyclerViewParagraphs.adapter = paragraphAdapter

        binding.bottomNav.selectedItemId = R.id.itemHome
        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.itemHome -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra(HomeActivity.user_login_cerbungHadiFamily, userId)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.itemFollowing -> {
                    val intent = Intent(this, FollowingActivity::class.java)
                    intent.putExtra(FollowingActivity.user_login_cerbungHadiFamily, userId)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.itemCreate -> {
                    val intent = Intent(this, Create1Activity::class.java)
                    intent.putExtra(Create1Activity.user_login_cerbungHadiFamily, userId)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.itemUser -> {
                    val intent = Intent(this, Users1Activity::class.java)
                    intent.putExtra(Users1Activity.user_login_cerbungHadiFamily, userId)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.itemPref -> {
                    val intent = Intent(this, PrefsActivity::class.java)
                    intent.putExtra(Users1Activity.user_login_cerbungHadiFamily, userId)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }

        binding.btnSubmit.setOnClickListener(){
            if (binding.txtInputEditSearch.text.toString() == ""){
                Toast.makeText(this, "Paragraf tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else {
                val q2 = Volley.newRequestQueue(this)
                val url2 = "https://ubaya.me/native/160721046/project/add_paragraph.php"

                val stringRequest2 = object: StringRequest(
                    Request.Method.POST, url2,
                    Response.Listener { response ->
                        Log.d("cekparams", response)

                        try {
                            val jsonResponse = JSONObject(response)
                            if (jsonResponse.getString("result") == "OK") {
                                Toast.makeText(this, "Berhasil menambahkan paragraf", Toast.LENGTH_SHORT).show()
                                binding.txtInputEditSearch.setText("")
                                refresh()
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    },
                    Response.ErrorListener { error ->
                        Log.d("cekparams", error.message.toString())
                        Toast.makeText(this, "Gagal menambahkan paragraf", Toast.LENGTH_SHORT).show()
                    }
                )
                {
                    override fun getParams(): MutableMap<String, String> {
                        val params = HashMap<String, String>()
                        params["cerbungID"] = cerbungId.toString()
                        params["userID"] = userId.toString()
                        params["content"] = binding.txtInputEditSearch.text.toString()
                        return params
                    }
                }
                q2.add(stringRequest2)
            }
        }

        binding.btnFollow.setOnClickListener(){
            if (binding.btnFollow.text.equals("Unfollow")){
                val q3 = Volley.newRequestQueue(this)
                val url3 = "https://ubaya.me/native/160721046/project/unfollow_cerbung.php"

                val stringRequest3 = object : StringRequest(
                    Request.Method.POST, url3,
                    { response ->
                        Log.d("apiresult", response)

                        try {
                            val jsonResponse = JSONObject(response)
                            val result = jsonResponse.getString("result")

                            if (result == "OK") {
                                Toast.makeText(this , "Berhasil unfollow", Toast.LENGTH_SHORT).show()
                                binding.btnFollow.text = "Follow"
                            } else {
                                Toast.makeText(this , "Cerbung belum difollow", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: JSONException) {
                            Log.e("apiresult", "error di try catch: " + e.message)
                        }
                    },
                    { error ->
                        Log.e("apiresult", error.message.toString())
                        Toast.makeText(this , "Cerbung belum difollow", Toast.LENGTH_SHORT).show()
                    })
                {
                    override fun getParams(): Map<String, String> {
                        val params = HashMap<String, String>()
                        params["userId"] = userId.toString()
                        params["cerbungId"] = cerbungId.toString()
                        return params
                    }
                }
                q3.add(stringRequest3)
            }else{
                val q3 = Volley.newRequestQueue(this)
                val url3 = "https://ubaya.me/native/160721046/project/follow_cerbung.php"

                val stringRequest3 = object : StringRequest(
                    Request.Method.POST, url3,
                    { response ->
                        Log.d("apiresult", response)

                        try {
                            val jsonResponse = JSONObject(response)
                            val result = jsonResponse.getString("result")

                            if (result == "OK") {
                                Toast.makeText(this , "Berhasil follow", Toast.LENGTH_SHORT).show()
                                binding.btnFollow.text = "Unfollow"
                            } else {
                                Toast.makeText(this , "Cerbung sudah difollow", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: JSONException) {
                            Log.e("apiresult", "error di try catch: " + e.message)
                        }
                    },
                    { error ->
                        Log.e("apiresult", error.message.toString())
                        Toast.makeText(this , "Cerbung sudah difollow", Toast.LENGTH_SHORT).show()
                    })
                {
                    override fun getParams(): Map<String, String> {
                        val params = HashMap<String, String>()
                        params["userId"] = userId.toString()
                        params["cerbungId"] = cerbungId.toString()
                        return params
                    }
                }
                q3.add(stringRequest3)
            }
        }



        binding.btnLike.setOnClickListener(){
            if (isLiked){
                val q3 = Volley.newRequestQueue(this)
                val url3 = "https://ubaya.me/native/160721046/project/unlike_cerbung.php"

                val stringRequest3 = object : StringRequest(
                    Request.Method.POST, url3,
                    { response ->
                        Log.d("apiresult", response)

                        try {
                            val jsonResponse = JSONObject(response)
                            val result = jsonResponse.getString("result")

                            if (result == "OK") {
                                Toast.makeText(this , "Berhasil unlike", Toast.LENGTH_SHORT).show()
                                binding.btnLike.setBackgroundResource(iconNotLiked)
                                isLiked = false
                                refresh()
                            } else {
                                Toast.makeText(this , "Cerbung belum dilike", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: JSONException) {
                            Log.e("apiresult", "error di try catch: " + e.message)
                        }
                    },
                    { error ->
                        Log.e("apiresult", error.message.toString())
                        Toast.makeText(this , "Cerbung belum dilike", Toast.LENGTH_SHORT).show()
                    })
                {
                    override fun getParams(): Map<String, String> {
                        val params = HashMap<String, String>()
                        params["userId"] = userId.toString()
                        params["cerbungId"] = cerbungId.toString()
                        return params
                    }
                }
                q3.add(stringRequest3)
            }else{
                val q3 = Volley.newRequestQueue(this)
                val url3 = "https://ubaya.me/native/160721046/project/like_cerbung.php"

                val stringRequest3 = object : StringRequest(
                    Request.Method.POST, url3,
                    { response ->
                        Log.d("apiresult", response)

                        try {
                            val jsonResponse = JSONObject(response)
                            val result = jsonResponse.getString("result")

                            if (result == "OK") {
                                Toast.makeText(this , "Berhasil like", Toast.LENGTH_SHORT).show()
                                binding.btnLike.setBackgroundResource(iconLiked)
                                isLiked = true
                                refresh()
                            } else {
                                Toast.makeText(this , "Cerbung sudah dilike", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: JSONException) {
                            Log.e("apiresult", "error di try catch: " + e.message)
                        }
                    },
                    { error ->
                        Log.e("apiresult", error.message.toString())
                        Toast.makeText(this , "Cerbung sudah dilike", Toast.LENGTH_SHORT).show()
                    })
                {
                    override fun getParams(): Map<String, String> {
                        val params = HashMap<String, String>()
                        params["userId"] = userId.toString()
                        params["cerbungId"] = cerbungId.toString()
                        return params
                    }
                }
                q3.add(stringRequest3)
            }
        }

        binding.btnRequest.setOnClickListener(){
            val q3 = Volley.newRequestQueue(this)
            val url3 = "https://ubaya.me/native/160721046/project/add_request.php"

            val stringRequest3 = object : StringRequest(
                Request.Method.POST, url3,
                { response ->
                    Log.d("apiresult", response)

                    try {
                        val jsonResponse = JSONObject(response)
                        val result = jsonResponse.getString("result")

                        if (result == "OK") {
                            Toast.makeText(this , "Berhasil mengirimkan request", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this , "Sudah pernah request sebelumnya", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: JSONException) {
                        Log.e("apiresult", "error di try catch: " + e.message)
                    }
                },
                { error ->
                    Log.e("apiresult", error.message.toString())
                    Toast.makeText(this , "Sudah pernah request sebelumnya", Toast.LENGTH_SHORT).show()
                })
            {
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["userId"] = userId.toString()
                    params["cerbungId"] = cerbungId.toString()
                    return params
                }
            }
            q3.add(stringRequest3)
        }
    }
    override fun onBackPressed() {
        binding.bottomNav.selectedItemId = R.id.itemHome
    }

    fun refresh() {
        val q = Volley.newRequestQueue(this)
        val url = "https://ubaya.me/native/160721046/project/read_cerbung.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener { response ->
                try {
                    val jsonResponse = JSONObject(response)
                    val jsonObjectCerbung = jsonResponse.getJSONObject("detail")
                    val type = jsonObjectCerbung.getString("type").toString()
                    Log.d("type", type +"halohalo")

                    binding.txtRestricted.visibility = View.VISIBLE

                    if (type == "Restricted") {
                        binding.txtRestricted.text = "Restricted"
                        binding.btnRequest.visibility = View.VISIBLE
                        binding.imageView15.visibility = View.GONE
                        binding.txtInputEditSearch.visibility = View.GONE
                        binding.btnSubmit.visibility = View.GONE
                        binding.txtInputLayoutSearch.visibility = View.GONE
                    }
                    else {
                        binding.txtRestricted.text = "Public"
                    }

                    val urlPhoto = jsonObjectCerbung.getString("urlPhoto").toString()
                    val title = jsonObjectCerbung.getString("title").toString()
                    val para = jsonResponse.getString("paragraph").toString()
                    val likes = jsonResponse.getString("likes").toString()
                    val genre = jsonObjectCerbung.getString("genre").toString()
                    val author = "by " + jsonObjectCerbung.getString("author").toString()
                    Log.d("type", urlPhoto)
                    Log.d("type", title)
                    Log.d("type", genre)
                    Log.d("type", author)

                    Picasso.get().load(urlPhoto).into(binding.imgPoster)
                    binding.txtJudul.text = title
                    binding.txtParagraph.text = para
                    binding.txtLike.text = likes
                    binding.txtGenre.text = genre
                    binding.txtCreator.text = author

                    val date = jsonObjectCerbung.getString("createDate")
                    binding.txtDate.text = date
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
                params["id"] = cerbungId.toString()
                return params
            }
        }
        q.add(stringRequest)

        val q4 = Volley.newRequestQueue(this)
        val url4 = "https://ubaya.me/native/160721046/project/cek_like_cerbung.php"

        val stringRequest4 = object : StringRequest(
            Request.Method.POST, url4,
            { response ->
                Log.d("apiresult", response)

                try {
                    val jsonResponse = JSONObject(response)
                    val result = jsonResponse.getString("result")

                    if (result == "Sudah like") {
                        binding.btnLike.setBackgroundResource(iconLiked)
                        isLiked = true
                    } else {
                        binding.btnLike.setBackgroundResource(iconNotLiked)
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
                params["cerbungId"] = cerbungId.toString()
                return params
            }
        }
        q4.add(stringRequest4)

        val q2 = Volley.newRequestQueue(this)
        val url2 = "https://ubaya.me/native/160721046/project/get_paragraph_for_cerbung.php"

        val stringRequest2 = object : StringRequest(
            Request.Method.POST, url2,
            { response ->
                Log.d("apiresult", response)

                try {
                    val jsonResponse = JSONObject(response)
                    val result = jsonResponse.getString("result")

                    if (result == "OK") {
                        val jsonArray = jsonResponse.getJSONArray("data")
                        val paragraphList = ArrayList<Paragraph>()

                        for (i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray.getJSONObject(i)
                            val id = jsonObject.getInt("id")
                            val cerbungId = jsonObject.getInt("cerbung_id")
                            val userId = jsonObject.getInt("user_id")
                            val content = jsonObject.getString("content")


                            val para = Paragraph(id, cerbungId, userId, content)
                            paragraphList.add(para)
                        }
                        paragraphAdapter.updateData(paragraphList)
                    } else {
                        Log.e("apiresult", "error API:" + jsonResponse.getString("message"))
                    }
                } catch (e: JSONException) {
                    Log.e("apiresult", "error get para: " + e.message)
                }
            },
            { error ->
                Log.e("apiresult", error.message.toString())
            })
        {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["id"] = cerbungId.toString()
                return params
            }
        }
        q2.add(stringRequest2)
    }


}