package com.dimas.uts_anmp_hobbyapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name="judul")
    var judul: String,

    @ColumnInfo(name="pembuat")
    var pembuat: String,

    @ColumnInfo(name="gambar")
    var gambar: String,

    @ColumnInfo(name="deskripsi")
    var deskripsi: String,
)
