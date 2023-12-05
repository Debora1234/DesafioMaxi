package com.example.myapplication.data.database

import android.content.Context
import android.util.Log
import androidx.room.*
import com.example.myapplication.data.database.dao.QuoteDao
import com.example.myapplication.data.database.dao.RazasDao
import com.example.myapplication.data.model.ListaRazasModel
import com.example.myapplication.data.database.entities.QuoteEntity
import com.example.myapplication.data.database.entities.RazasEntity


@Database(entities = [QuoteEntity::class, RazasEntity::class], version = 2, exportSchema = false)
abstract class QuoteDatabase : RoomDatabase() {
   public abstract fun getQuoteDao(): QuoteDao
   public abstract fun getRazasDao(): RazasDao
   companion object {
      @Volatile
      private var instance: QuoteDatabase? = null

      fun getInstance(context: Context): QuoteDatabase {
         if(instance != null) return instance!!
         synchronized(this){
            Log.d("estado ", "entre a getInstance")
            instance = Room
               .databaseBuilder(context, QuoteDatabase::class.java, "quote_database")
               .fallbackToDestructiveMigration()
               .build()
            return instance!!
         }

      }


   }

}




