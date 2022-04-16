package com.muzafferus.countrydetail.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.muzafferus.countrydetail.model.Country

@Dao
interface CountryDao {

    @Query("SELECT * FROM country_table ORDER BY countryCode ASC")
    fun getAllCountries(): Flow<List<Country>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(country: Country)

    @Query("SELECT * FROM country_table WHERE countryCode LIKE :id")
    fun getCountry(id:String): Country

    @Query("DELETE FROM country_table")
    suspend fun deleteAll()

}