package com.sidrakotlin.inventoryroom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sidrakotlin.inventoryroom.databinding.LayoutProductBinding

class ProductActivity : AppCompatActivity(){
    private lateinit var binding:LayoutProductBinding
    private lateinit var viewModel: AppViewModel
    private var id:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AppViewModel::class.java)

        val edit = intent.getStringExtra("type")
        if(edit.equals("Edit"))
        {
            id = intent.getIntExtra("id",0)
            val name = intent.getStringExtra("name")
            val price = intent.getDoubleExtra("price",0.0)
            val qty = intent.getIntExtra("qty",0)
            binding.prodNameEt.setText(name)
            binding.prodPriceEt.setText(""+price)
            binding.prodQtyEt.setText(""+qty)
        }
        binding.submit.setOnClickListener {
            var pname:String = binding.prodNameEt.text.toString()
            var pprice = binding.prodPriceEt.text.toString()
            var pqty = binding.prodQtyEt.text.toString()

            if(edit.equals("Edit")) {
                if (pname.isNotEmpty() && pprice.isNotEmpty() && pqty.isNotEmpty()) {
                    val updatepriceCv = pprice.toDouble()
                    val updateqtyCv = pqty.toInt()

                    val updateProd = Product(id, pname, updatepriceCv, updateqtyCv)
                    viewModel.update(updateProd)
                    Toast.makeText(this, "Product Updated Successfully!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else {
                    Toast.makeText(this, "Fields are required!", Toast.LENGTH_SHORT).show()

                }
            }
            else {
                if (pname.isNotEmpty() && pprice.isNotEmpty() && pqty.isNotEmpty()) {
                    val priceCv = pprice.toDouble()
                    val qtyCv = pqty.toInt()

                    val product = Product(0, pname, priceCv, qtyCv)
                    viewModel.insert(product)
                    Toast.makeText(this, "Product Added Successfully!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Fields are required!", Toast.LENGTH_SHORT).show()

                }
            }

        }
        binding.cancel.setOnClickListener {
            finish()
        }

    }

}