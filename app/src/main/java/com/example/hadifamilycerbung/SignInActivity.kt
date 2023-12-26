package com.example.hadifamilycerbung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hadifamilycerbung.databinding.ActivitySignInBinding
import org.json.JSONArray
import org.json.JSONObject

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    companion object {
        val user_login_cerbungHadiFamily = "random_16071239872_user"
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.txtDontHaveAcc.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.buttonLogin.setOnClickListener{
            if (binding.txtInputUsername.text.toString().trim().isEmpty()){
                binding.txtInputUsername.error = "Username cannot be empty"
            }
            else if(binding.txtInputPassword.text.toString().trim().isEmpty()){
                binding.txtInputPassword.error = "Password cannot be empty"
            }
            else{
                val q = Volley.newRequestQueue(this)
                val url = "https://ubaya.me/native/160721046/project/login.php"

                var stringRequest = object : StringRequest(Request.Method.POST, url,
                    {
                        Log.d("apiresult", it)
                        var obj = JSONObject(it)

                        var result = obj.getString("result")
                        if(result == "error"){
                            Toast.makeText(this, "Invalid Username / Password", Toast.LENGTH_LONG).show()
                        }
                        else if(result == "success")
                       {
                           var userId = obj.getInt("id")
                           Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show()

                           val intent = Intent(this, HomeActivity::class.java)
                           intent.putExtra(user_login_cerbungHadiFamily, userId)
                           finishAffinity()
                           startActivity(intent)
                       }
                    },
                    Response.ErrorListener {
                        Log.e("apiresult", it.message.toString())
                    })
                {
                    override fun getParams(): MutableMap<String, String>{
                        val params = HashMap<String, String>()
                        params["username"] = binding.txtInputUsername.text.toString()
                        params["password"] = binding.txtInputPassword.text.toString()

                        return params
                    }
                }
                q.add(stringRequest)
            }
        }
    }
}