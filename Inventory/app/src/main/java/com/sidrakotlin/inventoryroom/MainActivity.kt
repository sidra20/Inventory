package com.sidrakotlin.inventoryroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sidrakotlin.inventoryroom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ProductAdapter.updateClick {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AppViewModel
    private lateinit var adapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AppViewModel::class.java)

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, ProductActivity::class.java)
            startActivity(intent)
        }
        binding.productsRv.layoutManager = LinearLayoutManager(this)

        adapter = ProductAdapter(this@MainActivity,this)
        binding.productsRv.adapter = adapter



        viewModel.allProducts.observe(this, Observer {
            adapter.updateList(it)
        })


    }

    override fun update(product: Product) {

        val intent = Intent(this, ProductActivity::class.java)
        intent.putExtra("type", "Edit")
        intent.putExtra("name", product.name)
        intent.putExtra("price", product.price)
        intent.putExtra("qty", product.qty)
        intent.putExtra("id", product.id)
        startActivity(intent)
    }

    override fun delete(product: Product) {
        viewModel.delete(product)
        Toast.makeText(this, "Product Deleted Successfully!", Toast.LENGTH_LONG).show()

    }


}