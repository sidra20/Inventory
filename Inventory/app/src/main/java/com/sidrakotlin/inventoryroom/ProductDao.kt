package com.sidrakotlin.inventoryroom

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {

    @Insert
    fun insertProduct(product: Product)

    @Delete
    fun deleteProduct(product: Product)

    @Update
    fun updateProduct(product: Product)


    @Query("Select * from product order by name ASC")
    fun getAllProduct(): LiveData<List<Product>>

    @Query("DELETE FROM product")
    fun clearProducts()


}