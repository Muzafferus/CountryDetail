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
    val emoji: String?
)