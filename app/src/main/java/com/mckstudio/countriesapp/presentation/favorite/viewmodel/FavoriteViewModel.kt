package com.mckstudio.countriesapp.presentation.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mckstudio.countriesapp.Response
import com.mckstudio.countriesapp.data.mapper.dbToDetail
import com.mckstudio.countriesapp.data.mapper.toCountryDetailFromFavoriteScreen
import com.mckstudio.countriesapp.domain.repository.FavoriteRepository
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
    private val favoriteRepository: FavoriteRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteState())
    val state: StateFlow<FavoriteState> = _state

    fun getAllCountry() = viewModelScope.launch(Dispatchers.IO) {
        favoriteRepository
            .getAllCountry()
            .collectLatest { reponse ->
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
                            error = reponse.message,
                        )
                    }
                }

                is Response.Success ->  {
                    val transformData = reponse.data.map {
                        it.toCountryDetailFromFavoriteScreen()
                    }

                    _state.update { favoriteState ->
                        favoriteState.copy(
                            loading = false,
                            error = "",
                            favoriteList = transformData,
                        )
                    }
                }
            }
        }
    }

    fun performMigration() = viewModelScope.launch(Dispatchers.IO) {
        favoriteRepository.performMigrationIfNeeded()
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