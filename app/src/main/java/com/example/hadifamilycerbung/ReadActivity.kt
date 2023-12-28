package com.example.hadifamilycerbung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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

        val cerbungId = intent.getIntExtra(id_cerbungHadiFamily, 0)
        val userId = intent.getIntExtra(user_login_cerbungHadiFamily, 0)

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

                    if (type == "Restricted") {
                        binding.txtRestricted.visibility = View.VISIBLE
                        binding.imageView15.visibility = View.GONE
                        binding.txtInputEditSearch.visibility = View.GONE
                        binding.txtCharacters.visibility = View.GONE
                        binding.btnSubmit.visibility = View.GONE
                        binding.txtInputLayoutSearch.visibility = View.GONE
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

        val lm: LinearLayoutManager = LinearLayoutManager(this)
        binding.recyclerViewParagraphs.layoutManager = lm
        binding.recyclerViewParagraphs.setHasFixedSize(true)
        paragraphAdapter = ParagraphAdapter(paragraphs)
        binding.recyclerViewParagraphs.adapter = paragraphAdapter

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
    }
    override fun onBackPressed() {
        binding.bottomNav.selectedItemId = R.id.itemHome
    }
}