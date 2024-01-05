package com.example.hadifamilycerbung

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hadifamilycerbung.databinding.ActivityHomeBinding
import com.google.android.material.appbar.MaterialToolbar
import org.json.JSONException
import org.json.JSONObject


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var cerbungAdapter: CerbungAdapter

    companion object {
        val user_login_cerbungHadiFamily = "random_16071239872_user"
    }

    var cerbungs : ArrayList<Cerbung> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val userId = intent.getIntExtra(user_login_cerbungHadiFamily, 0)
        setSupportActionBar(binding.customToolbar.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.customToolbar.title.text = "Home"

        val lm: LinearLayoutManager = LinearLayoutManager(this)
        binding.recycleViewHome.layoutManager = lm
        binding.recycleViewHome.setHasFixedSize(true)
        cerbungAdapter = CerbungAdapter(cerbungs, userId)
        binding.recycleViewHome.adapter = cerbungAdapter

        val q = Volley.newRequestQueue(this)
        val url = "https://ubaya.me/native/160721046/project/get_cerbung.php"

        val stringRequest = object : StringRequest(
            Request.Method.GET, url,
            { response ->
                Log.d("apiresult", response)

                try {
                    val jsonResponse = JSONObject(response)
                    val result = jsonResponse.getString("result")

                    if (result == "OK") {
                        val jsonArray = jsonResponse.getJSONArray("data")
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
                            val createDate = jsonObject.getString("createDate")

                            val cerbung = Cerbung(id, title, urlPhoto, description, type, userId, genreId, createDate)
                            cerbungList.add(cerbung)
                        }
                        cerbungAdapter.updateData(cerbungList)
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

        }
        q.add(stringRequest)

        binding.bottomNav.selectedItemId = R.id.itemHome
        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.itemHome -> {
                    true
                }
                R.id.itemFollowing -> {
                    val intent = Intent(this, FollowingActivity::class.java)
                    intent.putExtra(FollowingActivity.user_login_cerbungHadiFamily, userId)
                    startActivity(intent)
                    true
                }
                R.id.itemCreate -> {
                    val intent = Intent(this, Create1Activity::class.java)
                    intent.putExtra(Create1Activity.user_login_cerbungHadiFamily, userId)
                    startActivity(intent)
                    true
                }
                R.id.itemUser -> {
                    val intent = Intent(this, Users1Activity::class.java)
                    intent.putExtra(Users1Activity.user_login_cerbungHadiFamily, userId)
                    startActivity(intent)
                    true
                }
                R.id.itemPref -> {
                    val intent = Intent(this, PrefsActivity::class.java)
                    intent.putExtra(user_login_cerbungHadiFamily, userId)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        binding.customToolbar.notifIcon.setOnClickListener{
            val intent = Intent(this, NotificationsActivity::class.java)
            intent.putExtra(NotificationsActivity.user_login_cerbungHadiFamily, userId)
            startActivity(intent)
            true
        }

        binding.customToolbar.menuIcon.setOnClickListener{
            binding.bottomNav.selectedItemId = R.id.itemHome
        }
    }
}