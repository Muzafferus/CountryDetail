package com.muzafferus.countrydetail.repository

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.muzafferus.coubtrydetail.CountriesListQuery
import com.muzafferus.coubtrydetail.CountryQuery
import com.muzafferus.countrydetail.network.CountryDetailApi
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val webService: CountryDetailApi
) : CountryRepository {
    override suspend fun queryCountriesList(): Response<CountriesListQuery.Data> {
        return webService.getApolloClient().query(CountriesListQuery()).await()
    }

    override suspend fun queryCountry(id: String): Response<CountryQuery.Data> {
        return webService.getApolloClient().query(CountryQuery(id)).await()
    }

}