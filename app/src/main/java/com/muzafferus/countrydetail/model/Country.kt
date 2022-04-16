package com.muzafferus.countrydetail.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country_table")
data class Country(
    @PrimaryKey
    @ColumnInfo(name = "countryCode")
    val code: String,

    @ColumnInfo(name = "countryName")
    val name: String,
    @ColumnInfo(name = "countryNative")
    val native_: String,
    @ColumnInfo(name = "countryPhone")
    val phone: String,
    @ColumnInfo(name = "countryCapital")
    val capital: String,
    @ColumnInfo(name = "countryCurrency")
    val currency: String,
    @ColumnInfo(name = "countryEmoji")
    val emoji: String
)