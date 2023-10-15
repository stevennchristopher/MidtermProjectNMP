package com.example.hadifamilycerbung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hadifamilycerbung.databinding.ActivitySignInBinding

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
            finish()
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
                for (user in Global.userData) {
                    if(user.username == usernameCheck && user.password == passwordCheck){
                        failCondition = 0

                        Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show()

                        val intent = Intent(this, HomeActivity::class.java)
                        intent.putExtra(user_login_cerbungHadiFamily, user.id)
                        startActivity(intent)
                        finish()
                        break
                    }
                    else{
                        failCondition++
                    }
                }

                if(failCondition > 0){
                    Toast.makeText(this, "Invalid Username / Password", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}