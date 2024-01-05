package com.example.hadifamilycerbung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hadifamilycerbung.databinding.ActivityCreate2Binding
import com.example.hadifamilycerbung.databinding.ActivityCreate3Binding
import com.example.hadifamilycerbung.databinding.ActivityHomeBinding
import com.google.android.material.appbar.MaterialToolbar
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.Locale

class Create3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCreate3Binding

    companion object {
        val title_cerbungHadiFamily = "title_random_1928391823"
        val description_cerbungHadiFamily = "description_random_1928391823"
        val imgUrl_cerbungHadiFamily = "url_random_1928391823"
        val genre_cerbungHadiFamily = "genre_random_1928391823"
        val user_login_cerbungHadiFamily = "random_16071239872_user"

        val access_cerbungHadiFamily = "access_19283918231607210"
        val paragraph_cerbungHadiFamily = "firstpara_19283918231607210"

        val rules_agree_cerbungHadiFamily = "rules_ryanryanryan10101010"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityCreate3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.customToolbar.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.customToolbar.title.text = "Create Cerbung"


        val title = intent.getStringExtra(title_cerbungHadiFamily)
        val description = intent.getStringExtra(description_cerbungHadiFamily)
        val urlPhoto = intent.getStringExtra(imgUrl_cerbungHadiFamily)
        val genre = intent.getIntExtra(genre_cerbungHadiFamily, 0)
        val userId = intent.getIntExtra(Create1Activity.user_login_cerbungHadiFamily, 0)
        val access = intent.getStringExtra(access_cerbungHadiFamily)
        val paragraph = intent.getStringExtra(paragraph_cerbungHadiFamily)
        val rulesCheck = intent.getStringExtra(Create1Activity.rules_agree_cerbungHadiFamily)

        binding.txtTitleCerbung.text = title
        binding.txtDescriptionCerbung.text = description
        binding.txtDescriptionCerbung2.text = paragraph
        binding.btnDisplayAccess.text = access
//        binding.btnDisplayGenre.text = genre

        if (rulesCheck == "yes"){
            binding.checkBoxTerms.isChecked = true
        }
        else {
            binding.checkBoxTerms.isChecked = false
        }

        binding.btnPublish.setOnClickListener{
            if (binding.checkBoxTerms.isChecked){
//                val newParagraph = Paragraph((countParagraph+1),(countCerbung+1), userId.toString().toInt(),paragraph.toString())
//                Global.paragraph.add(newParagraph)

                // project baru
                val q = Volley.newRequestQueue(this)
                val url = "https://ubaya.me/native/160721046/project/add_cerbung.php"
                var cerbungID = -1

                val stringRequest = object: StringRequest(
                    Request.Method.POST, url,
                    Response.Listener { response ->
                        Log.d("cekparams", response)

                        try {
                            val jsonResponse = JSONObject(response)
                            if (jsonResponse.getString("result") == "OK") {
                                cerbungID = jsonResponse.getInt("cerbungID")
                            }
                            val q2 = Volley.newRequestQueue(this)
                            val url2 = "https://ubaya.me/native/160721046/project/add_paragraph.php"

                            val stringRequest2 = object: StringRequest(
                                Request.Method.POST, url2,
                                Response.Listener { response ->
                                    Log.d("cekparams", response)

                                    try {
                                        val jsonResponse = JSONObject(response)
                                        if (jsonResponse.getString("result") == "OK") {
                                        }
                                    } catch (e: JSONException) {
                                        e.printStackTrace()
                                    }
                                },
                                Response.ErrorListener { error ->
                                    Log.d("cekparams", error.message.toString())
                                }
                            )
                            {
                                override fun getParams(): MutableMap<String, String> {
                                    val params = HashMap<String, String>()
                                    params["cerbungID"] = cerbungID.toString()
                                    params["userID"] = userId.toString()
                                    params["content"] = paragraph.toString()
                                    return params
                                }
                            }
                            q2.add(stringRequest2)
                            finish()
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    },
                    Response.ErrorListener { error ->
                        Log.d("cekparams", error.message.toString())
                    }
                )
                {
                    override fun getParams(): MutableMap<String, String> {
                        val params = HashMap<String, String>()
                        params["title"] = title.toString()
                        params["url"] = urlPhoto.toString()
                        params["description"] = description.toString()
                        params["type"] = access.toString()
                        params["userID"] = userId.toString()
                        params["genreID"] = (genre+1).toString()
                        return params
                    }
                }
                q.add(stringRequest)

                Toast.makeText(this, "Publish successful", Toast.LENGTH_LONG).show()
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra(user_login_cerbungHadiFamily, userId)
                finishAffinity()
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Please agree to our terms before publishing", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnPrevC3.setOnClickListener{
            val intent = Intent(this, Create2Activity::class.java)
            intent.putExtra(title_cerbungHadiFamily, title)
            intent.putExtra(description_cerbungHadiFamily, description)
            intent.putExtra(imgUrl_cerbungHadiFamily, urlPhoto)
            intent.putExtra(genre_cerbungHadiFamily, genre)
            intent.putExtra(user_login_cerbungHadiFamily, userId)
            intent.putExtra(access_cerbungHadiFamily, access)
            intent.putExtra(paragraph_cerbungHadiFamily, paragraph)

            var rulesCheck = "no"
            if (binding.checkBoxTerms.isChecked){
                rulesCheck = "yes"
            }

            intent.putExtra(rules_agree_cerbungHadiFamily, rulesCheck)

            startActivity(intent)
            finish()
        }

        binding.bottomNav.selectedItemId = R.id.itemCreate
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
                    intent.putExtra(Create1Activity.user_login_cerbungHadiFamily, userId)
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

        binding.customToolbar.menuIcon.setOnClickListener{
            binding.bottomNav.selectedItemId = R.id.itemHome
        }
    }
}