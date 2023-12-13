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
import com.example.hadifamilycerbung.databinding.ActivitySignUpBinding
import org.json.JSONObject

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.txtHaveAcc.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.buttonSignUp.setOnClickListener {
            val username = binding.txtInputUsernameNew.text.toString()
            val password = binding.txtInputPasswordNew.text.toString()
            val urlProfile = binding.txtInputProfilePictUrl.text.toString()
            val retypePassword = binding.txtInputPasswordConfirmNew.text.toString()

            if (binding.txtInputUsernameNew.text.toString().trim().isEmpty()) {
                binding.txtInputUsernameNew.error = "Username cannot be empty"
            } else if (binding.txtInputProfilePictUrl.text.toString().trim().isEmpty()) {
                binding.txtInputProfilePictUrl.error = "Profile Picture URL cannot be empty"
            } else if (binding.txtInputPasswordNew.text.toString().trim().isEmpty()) {
                binding.txtInputPasswordNew.error = "Password cannot be empty"
            } else if (binding.txtInputPasswordConfirmNew.text.toString().trim().isEmpty()) {
                binding.txtInputPasswordConfirmNew.error = "Re-Type Password cannot be empty"
            } else {
                val q = Volley.newRequestQueue(this)
                val url = "https://ubaya.me/native/160721046/project/cekUsername.php"

                var stringRequest = object : StringRequest(
                    Request.Method.POST, url,
                    {
                        Log.d("apiresult", it)
                        var obj = JSONObject(it)

                        var result = obj.getString("result")
                        if(result == "taken"){
                            var message = obj.getString("message")
                            Toast.makeText(this, message.toString(), Toast.LENGTH_LONG).show()
                        }
                        else if(result == "available")
                        {
                            if (password != retypePassword) {
                                Toast.makeText( this, "Password and retype password don't match.", Toast.LENGTH_LONG).show()
                            }
                            else if(password == retypePassword){
                                val urlRegister= "https://ubaya.me/native/160721046/project/registration.php"
                                var stringRequestRegister = object : StringRequest(
                                    Request.Method.POST, urlRegister,
                                    {
                                        Log.d("apiresult", it)
                                        var registrationObj  = JSONObject(it)
                                        var resultRegistration = registrationObj.getString("result")

                                        if(resultRegistration == "success"){
                                            Toast.makeText(this, "Sign Up Success.", Toast.LENGTH_LONG).show()

                                            val intent = Intent(this, SignInActivity::class.java)
                                            startActivity(intent)
                                            finish()
                                        }
                                    },
                                    Response.ErrorListener {
                                        Log.e("apiresult", it.message.toString())
                                    })
                                    {
                                        override fun getParams(): MutableMap<String, String>{
                                            val params = HashMap<String, String>()
                                            params["username"] = username
                                            params["urlPhoto"] = urlProfile
                                            params["password"] = password
                                            return params
                                        }
                                    }
                                q.add(stringRequestRegister)
                            }
                        }
                    },
                    Response.ErrorListener {
                        Log.e("apiresult", it.message.toString())
                    })
                {
                    override fun getParams(): MutableMap<String, String>{
                        val params = HashMap<String, String>()
                        params["username"] = username
                        return params
                    }
                }
                q.add(stringRequest)
            }
        }
    }
}