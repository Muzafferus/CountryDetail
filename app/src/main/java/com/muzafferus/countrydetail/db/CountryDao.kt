package com.muzafferus.countrydetail.db

import androidx.room.*
import com.muzafferus.countrydetail.model.Country
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Query("SELECT * FROM country_table ORDER BY countryCode ASC")
    fun getAllCountries(): Flow<List<Country>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(country: Country)

    @Update
    suspend fun update(country: Country)

    @Query("SELECT * FROM country_table WHERE countryCode LIKE :id")
    fun getCountry(id: String): Flow<Country>

    @Query("DELETE FROM country_table")
    suspend fun deleteAll()

}