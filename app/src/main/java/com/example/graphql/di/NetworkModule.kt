package com.example.graphql.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.graphql.api.CacheInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkhttpClientBuilder(): OkHttpClient.Builder =
        OkHttpClient.Builder().apply {
            connectTimeout(50L, TimeUnit.SECONDS)
            readTimeout(50L, TimeUnit.SECONDS)
            writeTimeout(50L, TimeUnit.SECONDS)
        }

    @Provides
    fun provideInterceptors(): InterceptorList {
        val interceptors = listOf(
            CacheInterceptor(),
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        return InterceptorList(interceptors)
    }

    @Provides
    fun provideOkHttpClient(
        builder: OkHttpClient.Builder,
        interceptorContainer: InterceptorList,
    ): OkHttpClient = builder.apply {
        interceptorContainer.interceptors.forEach { addInterceptor(it) }
    }.build()

    @Singleton
    @Provides
    fun provideApolloClient(
        okHttpClient: OkHttpClient
    ): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(BASE_URL)
            .okHttpClient(okHttpClient)
            .build()
    }

    companion object {
        private const val BASE_URL = "https://countries.trevorblades.com"
    }
}

data class InterceptorList(val interceptors: List<Interceptor>)