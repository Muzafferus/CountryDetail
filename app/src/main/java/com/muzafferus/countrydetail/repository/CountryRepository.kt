package com.muzafferus.countrydetail.repository

import com.apollographql.apollo.api.Response
import com.muzafferus.coubtrydetail.CountriesListQuery
import com.muzafferus.coubtrydetail.CountryQuery

interface CountryRepository {
    suspend fun queryCountriesList(): Response<CountriesListQuery.Data>
    suspend fun queryCountry(id: String): Response<CountryQuery.Data>
}