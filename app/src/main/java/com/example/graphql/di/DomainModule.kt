package com.example.graphql.di

import com.apollographql.apollo3.ApolloClient
import com.example.graphql.data.mapper.CountryMapper
import com.example.graphql.data.repository.CountryProvider
import com.example.graphql.data.repository.CountryRepository
import com.example.graphql.domain.usecase.CountryUseCase
import com.example.graphql.domain.usecase.CountryUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideCountryRepository(
        apolloClient: ApolloClient
    ) : CountryProvider = CountryRepository(apolloClient)

    @Provides
    fun provideCountryUseCase(
        newsProvider: CountryProvider,
        newsMapper: CountryMapper
    ): CountryUseCase = CountryUseCaseImpl(newsProvider, newsMapper)

    @Provides
    fun provideNewsMapper() = CountryMapper()
}