package com.muzafferus.countrydetail.model

import com.muzafferus.coubtrydetail.CountriesListQuery
import com.muzafferus.coubtrydetail.CountryQuery

class Mapper {
    companion object {
        fun CountryQuery.Data.toDataModel(): Country? = country?.let { element ->
            return Country(
                element.code,
                element.name,
                element.native_,
                element.phone,
                element.capital?:"",
                element.currency?:"",
                element.emoji
            )
        } ?: kotlin.run {
            return null
        }

        fun CountriesListQuery.Data.toDataModel(): List<Country> {
            countries.let { element ->
                val a: CountriesListQuery.Country = element[0]

                val countriesList = ArrayList<Country>()
                for (country in element) {
                    countriesList.add(country.toDataModel())
                }
                return countriesList
            }
        }

        private fun CountriesListQuery.Country.toDataModel(): Country = Country(
            this.code,
            this.name,
            "",
            "",
            this.capital?:"",
            "",
            this.emoji
        )
    }
}

