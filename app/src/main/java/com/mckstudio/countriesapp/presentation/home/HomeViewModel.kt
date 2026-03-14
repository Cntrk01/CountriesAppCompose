package com.mckstudio.countriesapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mckstudio.countriesapp.Response
import com.mckstudio.countriesapp.domain.model.CountryItem
import com.mckstudio.countriesapp.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchState(
    val error: String = "",
    val loading: Boolean = false,
    val searchCountry: List<CountryItem> = emptyList(),
)

data class RecommendedState(
    val error: String = "",
    val loading: Boolean = false,
    val recommendedCountry: List<CountryItem> = emptyList(),
)

@OptIn(FlowPreview::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")

    private val _searchState = MutableStateFlow(SearchState())
    val searchState: StateFlow<SearchState> = _searchState.asStateFlow()

    private val _recommendedState = MutableStateFlow(RecommendedState())
    val recommendedState: StateFlow<RecommendedState> = _recommendedState.asStateFlow()

    init {
       viewModelScope.launch(Dispatchers.IO) {
           _searchQuery
               .debounce(100)
               .filter {
                   it.isNotEmpty()
               }
               .distinctUntilChanged() //Aynı kelimeyi tekrar aramasın diye.
               .collectLatest { query ->
                   searchCountry(query)
               }
       }
    }

    fun onSearchQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
    }

    private fun searchCountry(query: String) = viewModelScope.launch(Dispatchers.IO){
        homeRepository
            .searchCountry(query = query)
            .collectLatest { response ->
                when (response) {
                    is Response.Loading -> {
                        _searchState.update {
                            it.copy(
                                error = "",
                                loading = true,
                            )
                        }
                    }

                    is Response.Error -> {
                        _searchState.update {
                            it.copy(
                                error = response.message,
                                loading = false,
                            )
                        }
                    }

                    is Response.Success -> {
                        _searchState.update {
                            it.copy(
                                error = "",
                                loading = false,
                                searchCountry = response.data
                            )
                        }
                    }
                }
        }
    }
}