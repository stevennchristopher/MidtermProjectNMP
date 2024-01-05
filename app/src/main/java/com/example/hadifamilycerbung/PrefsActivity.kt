package com.example.hadifamilycerbung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import com.android.volley.Request
import com.squareup.picasso.Picasso
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.RequestQueue
import com.example.hadifamilycerbung.databinding.ActivityPrefsBinding
import com.google.android.material.appbar.MaterialToolbar
import org.json.JSONObject

class PrefsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrefsBinding
    companion object {
        val user_login_cerbungHadiFamily = "random_16071239872_user"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrefsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.customToolbar.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.customToolbar.title.text = "Prefs"
        binding.textSimpenPassHidden.visibility = View.GONE

        val userId = intent.getIntExtra(HomeActivity.user_login_cerbungHadiFamily, 0)
        val q = Volley.newRequestQueue(this)
        val url = "https://ubaya.me/native/160721046/project/profile_data.php"
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                val jsonResponse = JSONObject(response)
                if (jsonResponse.getString("result") == "OK") {
                    var username = jsonResponse.getString("username").toString()
                    val password = jsonResponse.getString("password").toString()
                    val urlPhoto = jsonResponse.getString("urlPhoto").toString()

                    binding.txtUsernamePref.setText(username)
                    binding.textSimpenPassHidden.text = password

                    Picasso.get()
                        .load(urlPhoto)
                        .into(binding.profileImage)
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
                params["userId"] = userId.toString()
                return params
            }
        }
        q.add(stringRequest)

//        binding.txtUsername.keyListener = null

        binding.btnLogOut.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finishAffinity()
        }

        val darkModeSwitch = binding.btnDarkMode

        darkModeSwitch.isChecked = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES

        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        binding.btnChangePass.setOnClickListener {
            var oldPassword = binding.txtOldPassword.text.toString()
            var newPassword = binding.txtNewPassword.text.toString()
            var retypeNewPassword = binding.txtRetypeNewPassword.text.toString()

            var password = binding.textSimpenPassHidden.text.toString()

            if (oldPassword.isEmpty() || newPassword.isEmpty() || retypeNewPassword.isEmpty()) {
                Toast.makeText(applicationContext, "Please fill in all fields", Toast.LENGTH_LONG).show()
                binding.txtOldPassword.setText("")
                binding.txtNewPassword.setText("")
                binding.txtRetypeNewPassword.setText("")
            } else if (newPassword != retypeNewPassword) {
                Toast.makeText(applicationContext, "New password and retype password don't match", Toast.LENGTH_SHORT).show()
                binding.txtOldPassword.setText("")
                binding.txtNewPassword.setText("")
                binding.txtRetypeNewPassword.setText("")
            } else {
                if (oldPassword != password) {
                    Toast.makeText(applicationContext, "Incorrect Old Password", Toast.LENGTH_LONG).show()
                    binding.txtOldPassword.setText("")
                    binding.txtNewPassword.setText("")
                    binding.txtRetypeNewPassword.setText("")
                } else {
                    val urlChangePass = "https://ubaya.me/native/160721046/project/change_password.php"

                    val stringRequestChangePass = object : StringRequest(
                        Request.Method.POST, urlChangePass,
                        { response ->
                            val jsonResponse = JSONObject(response)
                            if (jsonResponse.getString("result") == "OK") {
                                Toast.makeText(applicationContext, "Password changed successfully", Toast.LENGTH_LONG).show()
                                binding.txtOldPassword.setText("")
                                binding.txtNewPassword.setText("")
                                binding.txtRetypeNewPassword.setText("")

                                password = newPassword
                                binding.textSimpenPassHidden.setText(password)

                                Log.d("hasilU", "berhasil change pass " + password)
                            }
                        },
                        Response.ErrorListener { error ->
                            Log.e("apiresult", "Error: ${error.message}", error)
                        }
                    ) {
                        override fun getParams(): Map<String, String> {
                            val params = HashMap<String, String>()
                            params["user_id"] = userId.toString()
                            params["new_password"] = newPassword
                            return params
                        }
                    }
                    q.add(stringRequestChangePass)
                }
            }
        }

        binding.bottomNav.selectedItemId = R.id.itemPref
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