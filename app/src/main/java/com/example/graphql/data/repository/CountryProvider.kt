package com.example.graphql.data.repository

import com.example.graphql.CountriesQuery
import com.example.graphql.CountryQuery

interface CountryProvider {
    suspend fun getCountries(): List<CountriesQuery.Country>
    suspend fun getCountry(code: String): CountryQuery.Country?
}