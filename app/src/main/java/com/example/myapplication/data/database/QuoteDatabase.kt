package com.example.myapplication.data.database

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.room.*
import com.example.myapplication.data.database.dao.QuoteDao
import com.example.myapplication.data.database.entities.QuoteEntity


@Database(entities = [QuoteEntity::class], version = 1, exportSchema = false)
abstract class QuoteDatabase : RoomDatabase() {
   public abstract fun getQuoteDao(): QuoteDao

   companion object {
      @Volatile
      private var instance: QuoteDatabase? = null

      fun getInstance(context: Context): QuoteDatabase {
         if(instance != null) return instance!!
         synchronized(this){
            Log.d("estado ", "entre a getInstance")
            instance = Room
               .databaseBuilder(context, QuoteDatabase::class.java, "quote_database")
               //.fallbackToDestructiveMigration()
               .build()
            return instance!!
         }

      }
   }
}
         /*fun getInstance(context: Context): QuoteDatabase {
         return instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
         }

      fun buildDatabase(context: Context): QuoteDatabase {
         return Room.databaseBuilder(context, QuoteDatabase::class.java, "quote_database")
            .build()
      }
   }*/



