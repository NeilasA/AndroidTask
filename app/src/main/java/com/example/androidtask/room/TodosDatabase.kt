package com.example.androidtask.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidtask.api.Data
import com.example.androidtask.api.TodosJson

@Database(version = 1, entities = [Data::class], exportSchema = false)
abstract class TodosDatabase : RoomDatabase() {


    //Creates Database

    abstract fun todosDao(): TodosDao

        companion object{
        @Volatile
        private var INSTANCE: TodosDatabase? = null

        fun getDatabase(context: Context): TodosDatabase{
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                        context.applicationContext,
                        TodosDatabase::class.java,
                        "todos_db"
                ).build().also{
                    INSTANCE = it

            }
            }

        }
    }

}