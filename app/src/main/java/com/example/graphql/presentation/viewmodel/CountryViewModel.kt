package com.example.graphql.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphql.domain.entity.DetailedCountry
import com.example.graphql.domain.entity.SimpleCountry
import com.example.graphql.domain.usecase.CountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val countryUseCase: CountryUseCase
) : ViewModel() {

    private val _countries = MutableStateFlow<List<SimpleCountry>>(listOf())
    val countries = _countries.asStateFlow()

    private val _countryDetail = MutableStateFlow<DetailedCountry?>(null)
    val countryDetail = _countryDetail.asStateFlow()

    init {
        viewModelScope.launch {
            _countries.update { countryUseCase.getCountries() }
        }
    }

    fun selectCountry(code: String) {
        viewModelScope.launch {
            _countryDetail.update { countryUseCase.getCountry(code) }
        }
    }
}