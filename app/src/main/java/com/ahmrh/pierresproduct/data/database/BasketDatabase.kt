package com.ahmrh.pierresproduct.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Basket:: class],
    version = 2,
    exportSchema = false
)
abstract class BasketDatabase : RoomDatabase() {

    abstract fun basketDao(): BasketDao

    companion object {
        @Volatile
        private var INSTANCE: BasketDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): BasketDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    BasketDatabase::class.java, "basket_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}