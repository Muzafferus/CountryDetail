package com.muzafferus.countrydetail.model

import com.muzafferus.coubtrydetail.CountriesListQuery
import com.muzafferus.coubtrydetail.CountryQuery

class Mapper {
    companion object {
        fun CountryQuery.Data.toDataModel(): Country? = country?.let { element ->
            val languages = ArrayList<Language>()
            for (language in element.languages) {
                languages.add(
                    Language(
                        language.code, language.name,
                        language.code, language.rtl
                    )
                )
            }
            val states = ArrayList<State>()
            for (state in element.states) {
                states.add(
                    State(state.code, state.name)
                )
            }
            return Country(
                element.code,
                element.name,
                element.native_,
                element.phone,
                element.capital,
                element.currency,
                element.emoji,
                Continent(element.continent.code, element.continent.name),
                languages,
                states
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
            null,
            null,
            this.capital,
            null,
            this.emoji,
            null, null, null
        )
    }
}

