package com.example.graphql.domain.usecase

import com.example.graphql.data.mapper.CountryMapper
import com.example.graphql.data.repository.CountryProvider
import com.example.graphql.domain.entity.DetailedCountry

class CountryUseCaseImpl(
    private val countryProvider: CountryProvider,
    private val countryMapper: CountryMapper
) : CountryUseCase {

    override suspend  fun getCountries() =
        countryMapper.toSimpleCountry(countryProvider.getCountries()).sortedBy { it.name }

    override suspend fun getCountry(code: String): DetailedCountry? {
        val detail = countryProvider.getCountry(code)
        return detail?.let {
            countryMapper.toDetailedCountry(it)
        }
    }
}