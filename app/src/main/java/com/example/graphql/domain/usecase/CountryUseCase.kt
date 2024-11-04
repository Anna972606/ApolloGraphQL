package com.example.graphql.domain.usecase

import com.example.graphql.domain.entity.DetailedCountry
import com.example.graphql.domain.entity.SimpleCountry

interface CountryUseCase {

    suspend fun getCountries(): List<SimpleCountry>

    suspend fun getCountry(code: String): DetailedCountry?
}