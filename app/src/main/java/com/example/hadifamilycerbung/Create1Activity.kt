package com.example.hadifamilycerbung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hadifamilycerbung.databinding.ActivityCreate1Binding
import com.example.hadifamilycerbung.databinding.ActivityHomeBinding
import com.squareup.picasso.Request
import org.json.JSONException
import org.json.JSONObject

class Create1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCreate1Binding
    private var genreList: List<Genre> = emptyList()

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
        binding = ActivityCreate1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.customToolbar.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.customToolbar.title.text = "Create Cerbung"

        val q = Volley.newRequestQueue(this)
        val url = "https://ubaya.me/native/160721046/project/get_genres.php"

        var genreId = intent.getIntExtra(genre_cerbungHadiFamily, 0)

        val stringRequest = StringRequest(
            com.android.volley.Request.Method.GET, url,
            Response.Listener { response ->
                try {
                    val jsonResponse = JSONObject(response)
                    if (jsonResponse.getString("result") == "OK") {
                        val genresArray = jsonResponse.getJSONArray("genres")

                        val genres = mutableListOf<Genre>()
                        for (i in 0 until genresArray.length()) {
                            val genreObject = genresArray.getJSONObject(i)
                            val genreId = genreObject.getInt("id")
                            val genreName = genreObject.getString("name")
                            genres.add(Genre(genreId, genreName))
                        }

                        genreList = genres

                        val adapter = ArrayAdapter(
                            this,
                            android.R.layout.simple_list_item_1,
                            genres.map { it.name })
                        binding.spinGenre.adapter = adapter
                        binding.spinGenre.setSelection(genreId)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                Log.d("Error", error.message.toString())
            }
        )

        q.add(stringRequest)


        val title = intent.getStringExtra(title_cerbungHadiFamily)
        val description = intent.getStringExtra(description_cerbungHadiFamily)
        val imgUrl = intent.getStringExtra(imgUrl_cerbungHadiFamily)
        val userId = intent.getIntExtra(user_login_cerbungHadiFamily, 0)
        val access = intent.getStringExtra(access_cerbungHadiFamily)
        val paragraph = intent.getStringExtra(paragraph_cerbungHadiFamily)
        val rulesCheck = intent.getStringExtra(rules_agree_cerbungHadiFamily)

        binding.txtInputTitle.setText(title)
        binding.txtInputDescription.setText(description)
        binding.txtInputImg.setText(imgUrl)


        binding.btnNextC1.setOnClickListener {
            if (binding.txtInputTitle.text.toString().trim().isEmpty()) {
                binding.txtInputTitle.error = "Title cannot be empty"
            } else if (binding.txtInputDescription.text.toString().trim().isEmpty()) {
                binding.txtInputDescription.error = "Description cannot be empty"
            } else if (binding.txtInputImg.text.toString().trim().isEmpty()) {
                binding.txtInputImg.error = "Image URL cannot be empty"
            } else {
                val title = binding.txtInputTitle.text.toString()
                val description = binding.txtInputDescription.text.toString()
                val imgCoverUrl = binding.txtInputImg.text.toString()
                val selectedGenre = genreList[binding.spinGenre.selectedItemPosition]
                genreId = selectedGenre.id-1

                val intent = Intent(this, Create2Activity::class.java)
                intent.putExtra(title_cerbungHadiFamily, title)
                intent.putExtra(description_cerbungHadiFamily, description)
                intent.putExtra(imgUrl_cerbungHadiFamily, imgCoverUrl)
                intent.putExtra(genre_cerbungHadiFamily, genreId)
                intent.putExtra(user_login_cerbungHadiFamily, userId)
                intent.putExtra(access_cerbungHadiFamily, access)
                intent.putExtra(paragraph_cerbungHadiFamily, paragraph)
                intent.putExtra(Create3Activity.rules_agree_cerbungHadiFamily, rulesCheck)
                startActivity(intent)
                finish()
            }
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

        binding.customToolbar.menuIcon.setOnClickListener{
            binding.bottomNav.selectedItemId = R.id.itemHome
        }
    }

    override fun onBackPressed() {
        binding.bottomNav.selectedItemId = R.id.itemHome
    }
}