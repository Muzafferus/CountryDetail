package com.muzafferus.countrydetail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.exception.ApolloException
import com.muzafferus.countrydetail.model.Country
import com.muzafferus.countrydetail.model.Mapper.Companion.toDataModel
import com.muzafferus.countrydetail.repository.CountryRepository
import com.muzafferus.countrydetail.view.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class CountryViewModel @Inject constructor(
    private val repository: CountryRepository,
) : ViewModel() {

    private val _countriesList by lazy { MutableLiveData<ViewState<List<Country>>>() }
    val countriesList: LiveData<ViewState<List<Country>>>
        get() = _countriesList

    private val _country by lazy { MutableLiveData<ViewState<Country>>() }
    val country: LiveData<ViewState<Country>>
        get() = _country

    private fun insert(country: Country) = viewModelScope.launch {
        repository.insert(country)
    }

    private fun update(country: Country) = viewModelScope.launch {
        repository.update(country)
    }

    private fun deleteAllDB() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun getCountriesList() {
        viewModelScope.launch {
            repository.getAllCountries().collect {
                if (it.isNullOrEmpty()) {
                    queryCountriesList()
                } else {
                    _countriesList.postValue(ViewState.Success(it))
                }
            }
        }
    }

    private fun queryCountriesList() = viewModelScope.launch {
        _countriesList.postValue(ViewState.Loading())
        try {
            val response = repository.queryCountriesList()
            val dataModel = response.data?.toDataModel()
            _countriesList.postValue(ViewState.Success(dataModel))
            deleteAllDB()
            insertDB(dataModel)
        } catch (e: ApolloException) {
            Log.d("ApolloException", "Failure", e)
            _countriesList.postValue(ViewState.Error("Error fetching characters"))
        }
    }

    private fun insertDB(dataModel: List<Country>?) {
        dataModel?.let {
            for (country in dataModel) {
                insert(country)
            }
        }

    }

    fun getCountryDetail(id: String) {
        viewModelScope.launch {
            repository.getCountry(id).collect {
                if (it.phone.isEmpty()) {
                    queryCountryData(id)
                } else {
                    _country.postValue(ViewState.Success(it))
                }
            }
        }
    }

    private fun queryCountryData(id: String) = viewModelScope.launch {
        _country.postValue(ViewState.Loading())
        try {
            val response = repository.queryCountry(id)
            _country.postValue(ViewState.Success(response.data?.toDataModel()))
            response.data?.toDataModel()?.let { update(it) }
        } catch (e: ApolloException) {
            Log.d("ApolloException", "Failure", e)
            _country.postValue(ViewState.Error("Error fetching characters"))
        }
    }


}