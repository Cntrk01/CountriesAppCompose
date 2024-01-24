package com.example.countriesapp.presentation.country_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesapp.data.response.Response
import com.example.countriesapp.domain.model.CountryItem
import com.example.countriesapp.domain.use_case.CountryListUseCase
import com.example.countriesapp.presentation.country_list.state.CountryListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(private val countryListUseCase: CountryListUseCase) :
    ViewModel() {

    private var PAGE_SIZE = 1
    private var countryList = listOf<CountryItem>()
    private var _state = MutableStateFlow(CountryListState())
    val countryListState: StateFlow<CountryListState> = _state

    init {
        getCountryList()
    }

    fun getCountryList() = viewModelScope.launch {
        countryListUseCase(page = PAGE_SIZE).collectLatest { response ->
            when (response) {
                is Response.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true
                        )
                    }
                }

                is Response.Error -> {
                    _state.update {
                        it.copy(
                            loading = false,
                            error = it.error
                        )
                    }
                }

                else -> {

                    response.data?.let {
                        countryList += it.map { countryItem ->
                            CountryItem(
                                flag = countryItem.flag,
                                name = countryItem.name
                            )
                        }
                    }

                    _state.update {
                        it.copy(
                            loading = false,
                            error = it.error,
                            countryData = countryList
                        )
                    }
                    PAGE_SIZE++
                }
            }
        }
    }
}