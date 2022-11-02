package com.sidrakotlin.inventoryroom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class AppViewModel(app: Application): AndroidViewModel(app) {

    val allProducts: LiveData<List<Product>>

    init{

        val dao = AppDatabase.getInstance(getApplication()).productDao()
        allProducts = dao.getAllProduct()
    }
    fun getProducts()
    {
        val dao = AppDatabase.getInstance(getApplication()).productDao()

        dao.getAllProduct()



    }

    fun insert(product: Product)
    {
        val dao = AppDatabase.getInstance(getApplication()).productDao()
        dao.insertProduct(product)
       // getProducts()

    }

    fun update(product: Product)
    {
        val dao = AppDatabase.getInstance(getApplication()).productDao()
        dao.updateProduct(product)
        getProducts()
    }

    fun delete(product: Product)
    {
        val dao = AppDatabase.getInstance(getApplication()).productDao()
        dao.deleteProduct(product)
        getProducts()

    }

}