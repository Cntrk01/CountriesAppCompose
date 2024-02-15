package com.mckstudio.countriesapp.presentation.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mckstudio.countriesapp.data.response.Response
import com.mckstudio.countriesapp.domain.use_case.SearchCountryUseCase
import com.mckstudio.countriesapp.presentation.search.state.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUseCase: SearchCountryUseCase): ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state : StateFlow<SearchState> = _state

    fun searchCountry(countryName:String) = viewModelScope.launch {
        searchUseCase
            .searchRepository(countryName = countryName)
            .collectLatest {
                when(it){
                    is Response.Loading->{
                        _state.update { updateState->
                            updateState.copy(
                                error = "",
                                loading = true,
                            )
                        }
                    }

                    is Response.Error->{
                        _state.update { updateState->
                            updateState.copy(
                                error = it.message.toString(),
                                loading = false,
                            )
                        }
                    }

                    else->{
                        _state.value= SearchState(
                            error = "",
                            loading = false,
                            searchCountry = it.data
                        )
                    }
                }
        }
    }

    fun resetState(){
        _state.update {
            it.copy(
                error = "",
                loading = false,
                searchCountry = emptyList()
            )
        }
    }
}