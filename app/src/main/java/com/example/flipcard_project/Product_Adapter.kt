package com.example.flipcard_project

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class Product_Adapter(private val itmlist:ArrayList<Product_Model>): RecyclerView.Adapter<Product_Adapter.Productholder>() {

    var List = ArrayList<Product_Model>()



    class Productholder(itemView: View) : ViewHolder(itemView) {

        val itmName : TextView = itemView.findViewById(R.id.rcvProductName)
        val itmRate : TextView = itemView.findViewById(R.id.rcvPrice)
        val itmImg : ImageView = itemView.findViewById(R.id.rcvimageSelect)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Productholder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_recycle_show,parent,false)
        return Productholder(itemView)
    }

    override fun getItemCount(): Int {
            return  itmlist.size
    }

    override fun onBindViewHolder(holder: Productholder, position: Int) {

        val currentItem = itmlist[position]
        holder.itmName.text = currentItem.product_name
        holder.itmRate.text = currentItem.product_rate

        val bytes = android.util.Base64.decode(currentItem.product_image,
            android.util.Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.size)
        holder.itmImg.setImageBitmap(bitmap)

    }

    fun setNote(temarr: ArrayList<Product_Model>) {
            this.List = temarr
        }


    }





