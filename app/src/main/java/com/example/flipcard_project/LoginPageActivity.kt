package com.example.flipcard_project

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.flipcard_project.databinding.AddBinding
import com.example.flipcard_project.databinding.LoginPageBinding
import com.google.firebase.auth.FirebaseAuth

class LoginPageActivity : AppCompatActivity() {

    lateinit var binding: LoginPageBinding
    lateinit var Auth: FirebaseAuth
    lateinit var SharedP: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnSubmit.setOnClickListener {
            var Email = binding.email.text.toString().trim()
            var Password = binding.password.text.toString().trim()

            if (Email.isEmpty()) {
                binding.email.error = "Enter Your Email"
                return@setOnClickListener
            }
            if (Password.isEmpty()) {
                binding.password.error = "Enter Your Password"
                return@setOnClickListener
            }

            Auth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Successful", Toast.LENGTH_LONG).show()
                    var intent = Intent(this, Home_Fragment::class.java)
                    startActivity(intent)
                }
            }.addOnFailureListener {

                Toast.makeText(this, "Failure", Toast.LENGTH_LONG).show()
            }

            //login page one time show

            editor = SharedP.edit()
            editor.putString("Email", Email)
            editor.putString("Password", Password)
            editor.apply()
            editor.commit()

        }
        binding.SignUp.setOnClickListener {
            var intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}