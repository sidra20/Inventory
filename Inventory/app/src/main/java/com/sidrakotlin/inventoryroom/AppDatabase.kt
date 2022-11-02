package com.sidrakotlin.inventoryroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase:RoomDatabase() {

    abstract fun productDao():ProductDao

    companion object{
        private var instance:AppDatabase?=null

        fun getInstance(context: Context):AppDatabase
        {

            if(instance==null)
            {

                synchronized(this)
                {


                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "inventorydb"
                    ).allowMainThreadQueries()
                        .build()
                }
            }
            return instance!!
        }

    }
}