package com.example.flipcard_project

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.flipcard_project.databinding.AddBinding
import com.example.flipcard_project.databinding.FragmentAddBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.io.ByteArrayOutputStream

class AddActivity : AppCompatActivity() {

        lateinit var binding :AddBinding
        lateinit var ref: DatabaseReference
        var Image: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddBinding.inflate(layoutInflater)
        setContentView(binding.root)


        }

        fun insert_Data(view: View) {
            var itemName = binding.productName.text.toString()
            var itemprice = binding.ProductPrice.text.toString()

            ref = FirebaseDatabase.getInstance().getReference("product")

            if (itemName.isEmpty()) {
                Toast.makeText(this, "Please Enter Product Name", Toast.LENGTH_SHORT).show()
            } else if (itemprice.isEmpty()) {
                Toast.makeText(this, "Please Enter Product Price", Toast.LENGTH_SHORT).show()
            } else {
                var item = Product_Model(itemName, itemprice, Image)
                var databaseref = FirebaseDatabase.getInstance().reference
                var id = databaseref.push().key
                ref.child(id.toString()).setValue(item).addOnSuccessListener {

                    binding.productName.text.clear()
                    binding.ProductPrice.text.clear()
                    Toast.makeText(this, " Successfully", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed ", Toast.LENGTH_SHORT).show()
                }
            }

        }

        fun image_select(view: View) {

            var myfileintent = Intent(Intent.ACTION_PICK)
            myfileintent.setType ("image/*")
            ActivityResultLauncher.launch(myfileintent)

        }
        private val ActivityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->

            if (result.resultCode == RESULT_OK) {
                val uri = result.data!!.data
                try {
                    val inputStreem = this?.contentResolver?.openInputStream(uri!!)
                    val myBitmap = BitmapFactory.decodeStream(inputStreem)
                    val stream = ByteArrayOutputStream()
                    myBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                    val bytes = stream.toByteArray()
                    Image = android.util.Base64.encodeToString(bytes, android.util.Base64.DEFAULT)
                    binding.imageSelect.setImageBitmap(myBitmap)
                    inputStreem!!.close()
                    Toast.makeText(this, "Image Selected", Toast.LENGTH_SHORT).show()

                } catch (ex: Exception) {
                    Toast.makeText(this, ex.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }

        }

    }
