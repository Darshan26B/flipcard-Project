package com.example.flipcard_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.flipcard_project.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(Home_Fragment())

        binding.ShoppingItem.setOnNavigationItemSelectedListener(object :BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {

                when (item.itemId) {
                    R.id.item_home -> {
                        loadFragment(Home_Fragment())
                    }
                    R.id.item_plus -> {
                        loadFragment(Add_Fragment())
                    }
                    R.id.item_Profile -> {
                        loadFragment(Profile_Fragment())
                    }
                }
                return true
            }

        })
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragFrame,fragment).commit()
    }
}