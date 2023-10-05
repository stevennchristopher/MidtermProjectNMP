package com.example.hadifamilycerbung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hadifamilycerbung.databinding.ActivityReadRestrictBinding
import com.example.hadifamilycerbung.databinding.ActivitySignInBinding
import com.example.hadifamilycerbung.databinding.ActivitySignUpBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    companion object {
        val USER_ID = "0"
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

            for (user in Global.userData) {
               if(user.username == usernameCheck && user.password == passwordCheck){
                   failCondition = 0

                   Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show()

                   intent.putExtra(USER_ID, user.id.toString())
                   val intent = Intent(this, HomeActivity::class.java)
                   startActivity(intent)
                   finish()
               }
                else{
                   failCondition++
                }
            }

            if(failCondition != 0){
                Toast.makeText(this, "Invalid Username / Password", Toast.LENGTH_LONG).show()

            }
        }
    }
}