package com.muzafferus.countrydetail.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muzafferus.countrydetail.model.Country

@Database(entities = [Country::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}
