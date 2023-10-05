package com.example.hadifamilycerbung

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hadifamilycerbung.databinding.ActivitySignInBinding
import com.example.hadifamilycerbung.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var counter = 0
        var countData = 0;

        binding.buttonSignUp.setOnClickListener{
            val username = binding.txtInputUsernameNew.text.toString()
            val password = binding.txtInputPasswordNew.text.toString()
            val url = binding.txtInputProfilePictUrl.text.toString()
            val retypePassword = binding.txtInputPasswordConfirmNew.text.toString()

            for (user in Global.userData){
                if(user.username == username){
                    counter++
                }
                else
                {
                    countData++
                }
            }

            if (counter == 0)
            {
                if (password == retypePassword)
                {
                    val newUser = User((countData+1),username,url,password)
                    Global.userData.add(newUser)
                    Toast.makeText(this, "Sign Up Success.", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    Toast.makeText(this, "Password and retype password don't match.", Toast.LENGTH_LONG).show()
                    counter = 0
                    countData = 0
                }
            }
            else
            {
                Toast.makeText(this, "Username unavailable.", Toast.LENGTH_LONG).show()
                counter = 0
                countData = 0
            }


        }
    }

}