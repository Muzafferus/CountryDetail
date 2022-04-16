package com.muzafferus.countrydetail.repository

import androidx.annotation.WorkerThread
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.muzafferus.coubtrydetail.CountriesListQuery
import com.muzafferus.coubtrydetail.CountryQuery
import com.muzafferus.countrydetail.db.CountryDao
import com.muzafferus.countrydetail.model.Country
import com.muzafferus.countrydetail.network.CountryDetailApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val countryDao: CountryDao,
    private val webService: CountryDetailApi
) : CountryRepository {

    @WorkerThread
    override suspend fun getAllCountries(): Flow<List<Country>> {
        return countryDao.getAllCountries()
    }

    @WorkerThread
    override suspend fun getCountry(id:String): Flow<Country>{
        return countryDao.getCountry(id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insert(country: Country) {
        countryDao.insert(country)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun update(country: Country) {
        countryDao.update(country)
    }

    override suspend fun queryCountriesList(): Response<CountriesListQuery.Data> {
        return webService.getApolloClient().query(CountriesListQuery()).await()
    }

    override suspend fun queryCountry(id: String): Response<CountryQuery.Data> {
        return webService.getApolloClient().query(CountryQuery(id)).await()
    }

    override suspend fun deleteAll() {
        countryDao.deleteAll()
    }

}