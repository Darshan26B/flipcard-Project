package com.example.flipcard_project

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.flipcard_project.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth


class Profile_Fragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    lateinit var editor: SharedPreferences.Editor
    lateinit var SharedP: SharedPreferences
    lateinit var user:FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)

        user = FirebaseAuth.getInstance()

        if (user.currentUser!=null) {
            user.currentUser?.let {
                binding.UserEmail.text = it.email
            }
        }

        binding.Logout.setOnClickListener {
//            editor = SharedP.edit()
//            editor.clear()
//            editor.apply()

            user.signOut()

            var intent = Intent(context, LoginPageActivity::class.java)
            startActivity(intent)
        }

         return binding.root
    }

 }