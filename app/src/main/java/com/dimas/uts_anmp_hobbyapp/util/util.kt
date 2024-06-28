package com.dimas.uts_anmp_hobbyapp.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.icu.text.CaseMap.Title
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dimas.uts_anmp_hobbyapp.model.CarDatabase
import com.dimas.uts_anmp_hobbyapp.model.UserDatabase

val DB_USER="newuserdb"
val DB_CAR="newcardb"


val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 not null")
    }
}

fun buildUserDb(context: Context): UserDatabase{
    val db = UserDatabase.buildDatabase(context)
    return db
}

fun buildCarDb(context: Context): CarDatabase{
    val db = CarDatabase.buildDatabase(context)
    return db
}
