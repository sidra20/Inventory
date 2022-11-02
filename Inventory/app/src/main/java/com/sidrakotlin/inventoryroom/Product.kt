package com.sidrakotlin.inventoryroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class Product(@PrimaryKey(autoGenerate = true)
                val id:Int=0,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "price")
    val price: Double?,

    @ColumnInfo(name = "qty")
    val qty: Int?


)