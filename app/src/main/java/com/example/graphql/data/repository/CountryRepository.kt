package com.example.graphql.data.repository

import com.apollographql.apollo3.ApolloClient
import com.example.graphql.CountriesQuery
import com.example.graphql.CountryQuery
import javax.inject.Inject

class CountryRepository @Inject constructor(
    private val apolloClient: ApolloClient
) : CountryProvider {

    override suspend fun getCountries(): List<CountriesQuery.Country> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries?: listOf()
    }

    override suspend fun getCountry(code: String): CountryQuery.Country? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
    }
}