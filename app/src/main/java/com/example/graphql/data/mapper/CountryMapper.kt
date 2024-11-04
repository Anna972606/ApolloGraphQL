package com.example.graphql.data.mapper

import com.example.graphql.CountriesQuery
import com.example.graphql.CountryQuery
import com.example.graphql.domain.entity.DetailedCountry
import com.example.graphql.domain.entity.SimpleCountry

class CountryMapper {

    fun toSimpleCountry(data: List<CountriesQuery.Country>) =
        data.map {
            SimpleCountry(
                code = it.code,
                name = it.name,
                emoji = it.emoji,
                capital = it.capital ?: "No capital",
            )
        }

    fun toDetailedCountry(data: CountryQuery.Country): DetailedCountry {
        return DetailedCountry(
            code = data.code,
            name = data.name,
            emoji = data.emoji,
            capital = data.capital ?: "No capital",
            currency = data.currency ?: "No currency",
            languages = data.languages.mapNotNull { it.name },
            continent = data.continent.name
        )
    }
}