package com.mckstudio.countriesapp.presentation.country_detail.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mckstudio.countriesapp.Response
import com.mckstudio.countriesapp.data.mapper.dbToDetail
import com.mckstudio.countriesapp.data.mapper.toCountryDetail
import com.mckstudio.countriesapp.data.mapper.toCreateFavorite
import com.mckstudio.countriesapp.domain.newmodel.CountryDetailEntity
import com.mckstudio.countriesapp.domain.repository.DetailRepository
import com.mckstudio.countriesapp.domain.repository.FavoriteRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = CountryDetailViewModel.CountryDetailFactory::class)
class CountryDetailViewModel @AssistedInject constructor(
    private val detailRepository: DetailRepository,
    private val favoriteRepository: FavoriteRepository,
    @Assisted private val name: String,
) : ViewModel() {

    private val _state = MutableStateFlow(CountryDetailState())
    val state: StateFlow<CountryDetailState> = _state.asStateFlow()

    @AssistedFactory
    interface CountryDetailFactory {
        fun create(name: String): CountryDetailViewModel
    }

    init {
        getCountry(name = name)
    }

    private fun getCountry(name: String) = viewModelScope.launch(Dispatchers.IO) {
        favoriteRepository
            .getFavoriteByName(name = name)
            .collect { response ->
                when (response) {
                    Response.Loading -> {
                        _state.update {
                            it.copy(isLoading = true)
                        }
                    }

                    is Response.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = true,
                                error = response.message
                            )
                        }
                    }

                    is Response.Success<*> -> {
                        val entity = response.data as? CountryDetailEntity
                        if (entity != null) {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    error = null,
                                    data = entity.dbToDetail(),
                                    isFavorite = true,
                                )
                            }
                        } else {
                            getCountryFromService(name)
                        }
                    }
                }
            }
    }

    private fun getCountryFromService(name: String) = viewModelScope.launch(Dispatchers.IO) {
        detailRepository
            .getCountryDetail(name = name)
            .collectLatest { response ->
                when (response) {
                    is Response.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true,
                                error = null,
                                isFavorite = false,
                            )
                        }
                    }

                    is Response.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = response.message,
                                isFavorite = false,
                            )
                        }
                    }

                    is Response.Success -> {
                        val countryDetailResponse = response.data.firstOrNull()
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = null,
                                data = countryDetailResponse?.toCountryDetail(),
                                isFavorite = false,
                            )
                        }
                    }
                }
            }
    }

    fun toggleFavorite() = viewModelScope.launch(Dispatchers.IO) {
        if (_state.value.isFavorite) {
            favoriteRepository
                .deleteCountry(countryName = name)
                .collectLatest { response ->
                    when (response) {
                        Response.Loading -> {}
                        is Response.Error -> {}
                        is Response.Success<*> -> {
                            _state.update {
                                it.copy(
                                    isFavorite = false,
                                )
                            }
                        }
                    }
                }
        } else {
            favoriteRepository
                .insertCountry(countryDetailItem = state.value.data!!.toCreateFavorite())
                .collectLatest { response ->
                    when (response) {
                        Response.Loading -> {}
                        is Response.Error -> {}
                        is Response.Success<*> -> {
                            _state.update {
                                it.copy(
                                    isFavorite = true
                                )
                            }
                        }
                    }
                }
        }
    }
}
