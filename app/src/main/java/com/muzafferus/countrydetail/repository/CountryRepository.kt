package com.muzafferus.countrydetail.repository

import com.apollographql.apollo.api.Response
import com.muzafferus.coubtrydetail.CountriesListQuery
import com.muzafferus.coubtrydetail.CountryQuery
import com.muzafferus.countrydetail.model.Country
import kotlinx.coroutines.flow.Flow

interface CountryRepository {
    suspend fun getAllCountries(): Flow<List<Country>>
    suspend fun insert(country: Country)
    suspend fun queryCountriesList(): Response<CountriesListQuery.Data>
    suspend fun queryCountry(id: String): Response<CountryQuery.Data>
    suspend fun deleteAll()
}