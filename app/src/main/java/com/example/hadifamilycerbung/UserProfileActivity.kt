package com.example.hadifamilycerbung

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hadifamilycerbung.databinding.ActivityHomeBinding
import com.example.hadifamilycerbung.databinding.ActivityUsersProfileBinding
import com.squareup.picasso.Picasso
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUsersProfileBinding
    private lateinit var cerbungProfile: CerbungProfileAdapter
    companion object {
        val user_login_cerbungHadiFamily = "random_16071239872_user"
        val id_userSelectedHadiFamily = "idcerbung_random_1928391823"
    }

    var cerbungsProfile : ArrayList<CerbungProfile> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityUsersProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.customToolbar.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        val userId = intent.getIntExtra(user_login_cerbungHadiFamily, 0)
        val selectedUserId = intent.getIntExtra(id_userSelectedHadiFamily, 0)
        Log.d("apiresult", userId.toString() + selectedUserId.toString())

        val q = Volley.newRequestQueue(this)
        val url = "https://ubaya.me/native/160721046/project/get_detailed_user_by_id.php"

        val stringRequest = @RequiresApi(Build.VERSION_CODES.O)
        object : StringRequest(
            Request.Method.POST, url,
            { response ->
                Log.d("apiresult", response)
                val jsonResponse = JSONObject(response)
                val result = jsonResponse.getString("result")

                if (result == "OK") {
                    var username = jsonResponse.getString("username").toString()
                    var urlPhoto = jsonResponse.getString("urlPhoto").toString()
                    var totalLikes = jsonResponse.getInt("totalLikes")
                    var totalCerbungs = jsonResponse.getInt("totalCerbungs")
                    var lastUpdated = jsonResponse.getString("createDate").toString()

                    Picasso.get()
                        .load(urlPhoto)
                        .into(binding.profileImageAtUserProfile)

                    binding.customToolbar.title.text = username
                    binding.txtUsernameUser.setText(username)
                    binding.txtLikesCerbungs.setText(totalLikes.toString() + " Likes | " + totalCerbungs.toString() + " Cerbungs Created")

                    val sinceJoin = LocalDateTime.parse(lastUpdated, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    binding.txtLatest.setText("Latest Post: " + sinceJoin.format(outputFormatter))

                    binding.txtCerbungs.setText(username + " Cerbungs")

                    val lm: LinearLayoutManager = LinearLayoutManager(this)
                    binding.recyclerViewProfile.layoutManager = lm
                    binding.recyclerViewProfile.setHasFixedSize(true)
                    cerbungProfile = CerbungProfileAdapter(cerbungsProfile)
                    binding.recyclerViewProfile.adapter = cerbungProfile

                    val urlCerbung = "https://ubaya.me/native/160721046/project/get_cerbung_by_userid_and_totalLikes.php"
                    var stringRequestCerbung = object : StringRequest(
                        Request.Method.POST, urlCerbung,
                        {
                            Log.d("apiresult", it)
                            val registrationObj  = JSONObject(it)
                            val resultRegistration = registrationObj.getString("result")

                            if(resultRegistration == "OK"){
                                val jsonArray = registrationObj.getJSONArray("dataCerbung")
                                val cerbungProfileList = ArrayList<CerbungProfile>()

                                for (i in 0 until jsonArray.length()) {
                                    val jsonObject = jsonArray.getJSONObject(i)
                                    val id = jsonObject.getInt("id")
                                    val title = jsonObject.getString("title")
                                    val urlPhoto = jsonObject.getString("urlPhoto")
                                    val createDate = jsonObject.getString("createDate")
                                    val cerbungLikes = jsonObject.getInt("totalLikes")

                                    val cerbungFix = CerbungProfile(id, title, urlPhoto, createDate, cerbungLikes)
                                    cerbungProfileList.add(cerbungFix)
                                }
                                cerbungProfile.updateData(cerbungProfileList)
                            }
                        },
                        Response.ErrorListener {
                            Log.e("apiresult", it.message.toString())
                        })
                    {
                        override fun getParams(): MutableMap<String, String>{
                            val params = HashMap<String, String>()
                            params["userId"] = selectedUserId.toString()
                            return params
                        }
                    }
                    q.add(stringRequestCerbung)
                }
                else {
                    Toast.makeText(this, "Gagal Mengambil Data Profile", Toast.LENGTH_LONG).show()
                }
            },
            Response.ErrorListener { error ->
                Log.e("apiresult", "Error: ${error.message}", error)
            }
        ) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["idUser"] = selectedUserId.toString()
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