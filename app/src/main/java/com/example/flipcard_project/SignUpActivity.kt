package com.example.flipcard_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.flipcard_project.databinding.SignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: SignUpBinding
    lateinit var Auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Auth = FirebaseAuth.getInstance()

        checkUserLogged()

        binding.btnSubmit.setOnClickListener {

            var Email = binding.email.text.toString().trim()
            var password = binding.password.text.toString().trim()

            if (Email.isEmpty()) {
                binding.email.error = "Enter Your Email"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                binding.password.error = "Enter Your Password"
                return@setOnClickListener
            }

            Auth.createUserWithEmailAndPassword(Email, password).addOnCompleteListener {
                if (it.isSuccessful) {

                    Toast.makeText(this, "Successful", Toast.LENGTH_LONG).show()
                }
            }.addOnFailureListener {

                Toast.makeText(this, "Failure", Toast.LENGTH_LONG).show()
            }
        }


        binding.LogInbtn.setOnClickListener {
            var intent = Intent(this, LoginPageActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

    private fun checkUserLogged() {
        if (Auth.currentUser != null) {

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }

}