package com.example.countriesapp.presentation.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesapp.data.response.Name
import com.example.countriesapp.data.response.Response
import com.example.countriesapp.domain.model.CountryRoomItem
import com.example.countriesapp.domain.use_case.FavoriteCountryUseCase
import com.example.countriesapp.presentation.favorite.state.FavoriteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteCountryUseCase: FavoriteCountryUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow(FavoriteState())
    val state: StateFlow<FavoriteState> = _state

    fun getAllCountry() = viewModelScope.launch {
        favoriteCountryUseCase.getAllCountry().collectLatest {
            when (it) {
                is Response.Loading -> {
                    _state.update { favoriteState ->
                        favoriteState.copy(
                            loading = true
                        )
                    }
                }

                is Response.Error -> {
                    _state.update { favoriteState ->
                        favoriteState.copy(
                            loading = false,
                            error = it.message.toString()
                        )
                    }
                }

                else -> {
                    _state.update { favoriteState ->
                        favoriteState.copy(
                            loading = false,
                            error = "",
                            favoriteList = it.data
                        )
                    }
                }
            }
        }
    }

    fun addCountry(countryDetailItem: CountryRoomItem) = viewModelScope.launch {
        favoriteCountryUseCase
            .insertCountry(countryDetailItem = countryDetailItem)
            .collectLatest {
                when (it) {
                    is Response.Loading -> {
                        _state.update { favoriteState ->
                            favoriteState.copy(
                                loading = true
                            )
                        }
                    }

                    is Response.Error -> {
                        _state.update { favoriteState ->
                            favoriteState.copy(
                                loading = false,
                                error = it.message.toString()
                            )
                        }
                    }

                    else -> {
                        _state.update { favoriteState ->
                            favoriteState.copy(
                                loading = false,
                                error = "",
                                countryAdded = true
                            )
                        }
                    }
                }
            }
    }

    fun deleteCountry(countryDetailItem: CountryRoomItem) = viewModelScope.launch {
        favoriteCountryUseCase
            .deleteCountry(countryDetailItem = countryDetailItem)
            .collectLatest {
                when (it) {
                    is Response.Loading -> {
                        _state.update { favoriteState ->
                            favoriteState.copy(
                                loading = true
                            )
                        }
                    }

                    is Response.Error -> {
                        _state.update { favoriteState ->
                            favoriteState.copy(
                                loading = false,
                                error = it.message.toString()
                            )
                        }
                    }

                    else -> {
                        _state.update { favoriteState ->
                            favoriteState.copy(
                                loading = false,
                                error = "",
                                countryDeleted = true
                            )
                        }
                    }
                }
            }
    }

    fun checkExistCountry(name: Name) = viewModelScope.launch {
        favoriteCountryUseCase
            .checkExistCountry(name = name)
            .collectLatest {
                when (it) {
                    is Response.Loading -> {
                        _state.update { favoriteState ->
                            favoriteState.copy(
                                loading = true
                            )
                        }
                    }

                    is Response.Error -> {
                        _state.update { favoriteState ->
                            favoriteState.copy(
                                loading = false,
                                error = it.message.toString()
                            )
                        }
                    }

                    else -> {
                        _state.update { favoriteState ->
                            favoriteState.copy(
                                loading = false,
                                error = "",
                                checkExists = it.data
                            )
                        }
                    }
                }
            }
    }

    fun resetState() {
        _state.update {
            it.copy(
                loading = false,
                error = "",
                favoriteList = null,
                countryAdded = false,
                countryDeleted = false,
                checkExists = 0
            )
        }
    }
}