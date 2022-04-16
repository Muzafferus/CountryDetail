package com.muzafferus.countrydetail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.muzafferus.coubtrydetail.CountriesListQuery
import com.muzafferus.coubtrydetail.CountryQuery
import com.muzafferus.countrydetail.repository.CountryRepository
import com.muzafferus.countrydetail.view.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class CountryViewModel @Inject constructor(
    private val repository: CountryRepository,
) : ViewModel() {

    private val _countriesList by lazy { MutableLiveData<ViewState<Response<CountriesListQuery.Data>>>() }
    val countriesList: LiveData<ViewState<Response<CountriesListQuery.Data>>>
        get() = _countriesList

    private val _country by lazy { MutableLiveData<ViewState<Response<CountryQuery.Data>>>() }
    val country: LiveData<ViewState<Response<CountryQuery.Data>>>
        get() = _country


    fun queryCountriesList() = viewModelScope.launch {
        _countriesList.postValue(ViewState.Loading())
        try {
            val response = repository.queryCountriesList()
            _countriesList.postValue(ViewState.Success(response))
        } catch (e: ApolloException) {
            Log.d("ApolloException", "Failure", e)
            _countriesList.postValue(ViewState.Error("Error fetching characters"))
        }
    }

    fun queryCountryData(id: String) = viewModelScope.launch {
        _country.postValue(ViewState.Loading())
        try {
            val response = repository.queryCountry(id)
            _country.postValue(ViewState.Success(response))
        } catch (e: ApolloException) {
            Log.d("ApolloException", "Failure", e)
            _country.postValue(ViewState.Error("Error fetching characters"))
        }
    }


}