package com.example.hadifamilycerbung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hadifamilycerbung.databinding.ActivityFollowingBinding
import org.json.JSONException
import org.json.JSONObject

class FollowingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFollowingBinding
    private lateinit var cerbungFollowingAdapter: FollowingAdapter

    companion object {
        val user_login_cerbungHadiFamily = "random_16071239872_user"
    }

    var followingCerbungs : ArrayList<Cerbung> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityFollowingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.customToolbar.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.customToolbar.title.text = "Following"

        val userId = intent.getIntExtra(user_login_cerbungHadiFamily, 0)
        Log.d("apiresult", userId.toString())

        val lm: LinearLayoutManager = LinearLayoutManager(this)
        binding.recycleViewFollowing.layoutManager = lm
        binding.recycleViewFollowing.setHasFixedSize(true)
        cerbungFollowingAdapter = FollowingAdapter(followingCerbungs, userId)
        binding.recycleViewFollowing.adapter = cerbungFollowingAdapter

        val q = Volley.newRequestQueue(this)
        val url = "https://ubaya.me/native/160721046/project/get_cerbung_following.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                Log.d("apiresult", response)

                try {
                    val jsonResponse = JSONObject(response)
                    val result = jsonResponse.getString("result")

                    if (result == "OK") {
                        val jsonArray = jsonResponse.getJSONArray("dataId")
                        val cerbungFollowingList = ArrayList<CerbungFollowing>()

                        for (i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray.getJSONObject(i)
                            val idCerbung = jsonObject.getInt("cerbung_id")

                            val cerbungFollowing = CerbungFollowing(idCerbung)
                            cerbungFollowingList.add(cerbungFollowing)
                        }

                        val urlCerbung = "https://ubaya.me/native/160721046/project/get_cerbung_by_id.php"
                        var stringRequestCerbung = object : StringRequest(
                            Request.Method.POST, urlCerbung,
                            {
                                Log.d("apiresult", it)
                                val registrationObj  = JSONObject(it)
                                val resultRegistration = registrationObj.getString("result")

                                if(resultRegistration == "OK"){
                                    val jsonArray = registrationObj.getJSONArray("data")
                                    val cerbungList = ArrayList<Cerbung>()

                                    for (i in 0 until jsonArray.length()) {
                                        val jsonObject = jsonArray.getJSONObject(i)
                                        val id = jsonObject.getInt("id")
                                        val title = jsonObject.getString("title")
                                        val urlPhoto = jsonObject.getString("urlPhoto")
                                        val description = jsonObject.getString("description")
                                        val type = jsonObject.getString("type")
                                        val userId = jsonObject.getInt("user_id")
                                        val genreId = jsonObject.getInt("genre_id")
                                        val lastUpdate = jsonObject.getString("lastUpdated")

                                        val cerbungFix = Cerbung(id, title, urlPhoto, description, type, userId, genreId, lastUpdate)
                                        cerbungList.add(cerbungFix)
                                    }
                                    cerbungFollowingAdapter.updateData(cerbungList)
                                }
                            },
                            Response.ErrorListener {
                                Log.e("apiresult", it.message.toString())
                            })
                        {
                            override fun getParams(): MutableMap<String, String>{
                                val params = HashMap<String, String>()

                                val ids = cerbungFollowingList.map { it.id.toString() }
                                params["id"] = ids.joinToString(",")

                                Log.d("apiresult", ids.toString())

                                return params
                            }
                        }
                        q.add(stringRequestCerbung)

                    } else {
                        Log.e("apiresult", "error API:" + jsonResponse.getString("message"))
                    }
                } catch (e: JSONException) {
                    Log.e("apiresult", "error di try catch: " + e.message)
                }
            },
            { error ->
                Log.e("apiresult", error.message.toString())
            })
        {
            override fun getParams(): MutableMap<String, String>{
                val params = HashMap<String, String>()
                params["idUser"] = userId.toString()
                return params
            }
        }
        q.add(stringRequest)

        binding.bottomNav.selectedItemId = R.id.itemFollowing
        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.itemHome -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra(user_login_cerbungHadiFamily, userId)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.itemFollowing -> {
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
                    intent.putExtra(user_login_cerbungHadiFamily, userId)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }

        binding.customToolbar.notifIcon.setOnClickListener{
            val intent = Intent(this, NotificationsActivity::class.java)
            intent.putExtra(NotificationsActivity.user_login_cerbungHadiFamily, userId)
            startActivity(intent)
            finish()
            true
        }
    }

    override fun onBackPressed() {
        binding.bottomNav.selectedItemId = R.id.itemHome
    }
}