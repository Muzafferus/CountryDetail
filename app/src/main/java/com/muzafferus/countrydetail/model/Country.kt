package com.muzafferus.countrydetail.model

data class Countries(
    val countries: List<Country>
)

data class Country(
    val code: String,
    val name: String?,
    val native: String? = null,
    val phone: String? = null,
    val capital: String?,
    val currency: String? = null,
    val emoji: String?,
    val continent: Continent? = null,
    val languages: List<Language>? = null,
    val states: List<State>? = null
)

data class Continent(
    val code: String,
    val name: String?
)

data class Language(
    val code: String,
    val name: String?,
    val native: String?,
    val rtl: Boolean?
)

data class State(
    val code: String?,
    val name: String?
)
