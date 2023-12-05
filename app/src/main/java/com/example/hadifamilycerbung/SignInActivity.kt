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

        var failCondition = 0

        binding.buttonLogin.setOnClickListener{
            val usernameCheck = binding.txtInputUsername.text.toString()
            val passwordCheck = binding.txtInputPassword.text.toString()

            if (binding.txtInputUsername.text.toString().trim().isEmpty()){
                binding.txtInputUsername.error = "Username cannot be empty"
            }
            else if(binding.txtInputPassword.text.toString().trim().isEmpty()){
                binding.txtInputPassword.error = "Password cannot be empty"
            }
            else{
                val q = Volley.newRequestQueue(this)
                val url = "https://ubaya.me/native/160721046/project/login.php"

                var stringRequest = object : StringRequest(
                    Request.Method.POST, url,
                    Response.Listener<String> {
                        Log.d("apiresult", it)

                        val jsonObject = JSONObject(it)
                        val result = jsonObject.getInt("result")

                       if(result.toString() == "error"){
                           Log.d("hasil", "error")
                       }
                        else if(result.toString() == "success")
                       {
                           val idUser = jsonObject.getInt("id")
                           Log.d("hasil", idUser.toString())
                       }
                    },
                    Response.ErrorListener {
                        Log.e("apiresult", it.message.toString())
                        Log.d("hasil", "error")
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


//                for (user in Global.userData) {
//                    if(user.username == usernameCheck && user.password == passwordCheck){
//                        failCondition = 0
//
//                        Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show()
//
//                        val intent = Intent(this, HomeActivity::class.java)
//                        intent.putExtra(user_login_cerbungHadiFamily, user.id)
//                        finishAffinity()
//                        startActivity(intent)
//                        break
//                    }
//                    else{
//                        failCondition++
//                    }
//                }
//
//                if(failCondition > 0){
//                    Toast.makeText(this, "Invalid Username / Password", Toast.LENGTH_LONG).show()
//                }
            }
        }
    }
}