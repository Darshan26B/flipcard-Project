package com.example.flipcard_project

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flipcard_project.databinding.FragmentHomeBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Home_Fragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private lateinit var reference: DatabaseReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemlist: ArrayList<Product_Model>
    lateinit var adapter: Product_Adapter
    var NoteList = ArrayList<Product_Model>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)


        recyclerView = binding.rcvProduct
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.hasFixedSize()
        itemlist = arrayListOf<Product_Model>()

 


        getItemData()
         return binding.root
    }


    private fun getItemData() {
        reference = FirebaseDatabase.getInstance().getReference("product")
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {

                    for (list in snapshot.children) {
                        val data = list.getValue(Product_Model::class.java)
                        itemlist.add(data!!)
                    }
                    recyclerView.adapter = Product_Adapter(itemlist)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

}