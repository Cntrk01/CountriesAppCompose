package com.mckstudio.countriesapp.presentation.favorite.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mckstudio.countriesapp.common.Constants.FAVORITE
import com.mckstudio.countriesapp.data.response.Name
import com.mckstudio.countriesapp.data.response.Response
import com.mckstudio.countriesapp.domain.model.CountryDetailItem
import com.mckstudio.countriesapp.domain.use_case.FavoriteCountryUseCase
import com.mckstudio.countriesapp.presentation.favorite.state.FavoriteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteCountryUseCase: FavoriteCountryUseCase,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    init {
        savedStateHandle.get<String>(FAVORITE)?.let {
            if (it == FAVORITE) {
                getAllCountry()
            }
        }
    }

    private val _state = MutableStateFlow(FavoriteState())
    val state: StateFlow<FavoriteState> = _state

    fun getAllCountry() = viewModelScope.launch(Dispatchers.IO) {
        favoriteCountryUseCase.getAllCountry().collectLatest { reponse ->
            when (reponse) {
                is Response.Loading -> {
                    _state.update { favoriteState ->
                        favoriteState.copy(
                            error = "",
                            loading = true
                        )
                    }
                }

                is Response.Error -> {
                    _state.update { favoriteState ->
                        favoriteState.copy(
                            loading = false,
                            error = reponse.message.toString()
                        )
                    }
                }

                else -> {
                    _state.update { favoriteState ->
                        favoriteState.copy(
                            loading = false,
                            error = "",
                            favoriteList = reponse.data
                        )
                    }
                }
            }
        }
    }

    fun addCountry(countryDetailItem: CountryDetailItem) = viewModelScope.launch(Dispatchers.IO) {
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
                            )
                        }
                    }
                }
            }
    }

    fun deleteCountry(countryDetailItem: CountryDetailItem) =
        viewModelScope.launch(Dispatchers.IO) {
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

    fun checkExistCountry(name: Name) = viewModelScope.launch(Dispatchers.IO) {
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
                countryDeleted = false,
                checkExists = 0
            )
        }
    }
}