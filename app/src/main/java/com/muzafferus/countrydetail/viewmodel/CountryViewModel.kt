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

    fun getCountriesList() {
        //TODO: GET DB LIST
        //IF DB EMPTY OR NULL
        //GET API REQUEST
        queryCountriesList()
    }

    //fun dbCountriesList(){}

    private fun queryCountriesList() = viewModelScope.launch {
        _countriesList.postValue(ViewState.Loading())
        try {
            val response = repository.queryCountriesList()
            _countriesList.postValue(
                ViewState.Success(response.data?.toDataModel())
            )
        } catch (e: ApolloException) {
            Log.d("ApolloException", "Failure", e)
            _countriesList.postValue(ViewState.Error("Error fetching characters"))
        }
    }

    fun getCountryDetail(id: String) {
        //TODO: GET DB LIST
        //IF DB EMPTY OR NULL
        //GET API REQUEST
        queryCountryData(id)
    }

    //fun dbCountryDetail(id:String){}

    private fun queryCountryData(id: String) = viewModelScope.launch {
        _country.postValue(ViewState.Loading())
        try {
            val response = repository.queryCountry(id)
            _country.postValue(ViewState.Success(response.data?.toDataModel()))
        } catch (e: ApolloException) {
            Log.d("ApolloException", "Failure", e)
            _country.postValue(ViewState.Error("Error fetching characters"))
        }
    }


}