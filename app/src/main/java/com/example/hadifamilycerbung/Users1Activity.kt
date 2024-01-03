package com.example.hadifamilycerbung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hadifamilycerbung.databinding.ActivityHomeBinding
import com.example.hadifamilycerbung.databinding.ActivityUsers1Binding
import org.json.JSONException
import org.json.JSONObject

class Users1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityUsers1Binding
    private lateinit var userAdapter: UserAdapter

    companion object {
        val user_login_cerbungHadiFamily = "random_16071239872_user"
    }

    var users : ArrayList<UserInUser> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityUsers1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.customToolbar.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.customToolbar.title.text = "Users"

        val userId = intent.getIntExtra(HomeActivity.user_login_cerbungHadiFamily, 0)
        Log.d("apiresult", userId.toString())

        val lm: LinearLayoutManager = LinearLayoutManager(this)
        binding.recycleViewUser.layoutManager = lm
        binding.recycleViewUser.setHasFixedSize(true)
        userAdapter = UserAdapter(users, userId)
        binding.recycleViewUser.adapter = userAdapter

        val q = Volley.newRequestQueue(this)
        val url = "https://ubaya.me/native/160721046/project/get_user_without_iduser.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            {response ->
                Log.d("apiresult", response)
                val jsonResponse = JSONObject(response)
                val result = jsonResponse.getString("result")

                if (result == "OK") {
                    val jsonArray = jsonResponse.getJSONArray("dataUser")
                    val userList = ArrayList<UserInUser>()

                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val id = jsonObject.getInt("id")
                        val username = jsonObject.getString("username")
                        val urlPhoto = jsonObject.getString("urlPhoto")
                        val createDate = jsonObject.getString("createdAt")
                        val totalLikes = jsonObject.getInt("totalLikes")

                        val userFix = UserInUser(id, username, urlPhoto, createDate, totalLikes)
                        userList.add(userFix)
                        Log.d("Coba", "hemm")
                    }

                    userAdapter.updateData(userList)
                }
            },
            Response.ErrorListener {
                Log.e("apiresult", it.message.toString())
            })
        {
            override fun getParams(): MutableMap<String, String>{
                val params = HashMap<String, String>()
                params["idUser"] = userId.toString()
                return params
            }
        }
        q.add(stringRequest)

        binding.bottomNav.selectedItemId = R.id.itemUser
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