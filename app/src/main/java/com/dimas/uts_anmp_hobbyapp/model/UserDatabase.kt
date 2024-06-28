package com.dimas.uts_anmp_hobbyapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dimas.uts_anmp_hobbyapp.util.DB_USER
import com.dimas.uts_anmp_hobbyapp.util.MIGRATION_1_2

@Database(entities = arrayOf(User::class), version = 1) //kalau versi 2 error gatau kenapa
abstract class UserDatabase:RoomDatabase() {
    abstract fun UserDao(): UserDao
    companion object{
        @Volatile private var instance: UserDatabase ?= null
        private val LOCK = Any()

        fun buildDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java, DB_USER)
                .addMigrations(MIGRATION_1_2)
                .build()

        operator fun invoke(context: Context){
            if(instance != null){
                synchronized(LOCK){
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}