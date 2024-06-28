package com.dimas.uts_anmp_hobbyapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = false)
    var iduser: String,

    @ColumnInfo(name="email")
    var email: String,

    @ColumnInfo(name="nama_depan")
    var nama_depan: String,

    @ColumnInfo(name="nama_belakang")
    var nama_belakang: String,

    @ColumnInfo(name="password")
    var password: String
)
