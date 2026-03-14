package com.mckstudio.countriesapp.presentation.region_subregion.regionlist.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mckstudio.countriesapp.common.Constants
import com.mckstudio.countriesapp.Response
import com.mckstudio.countriesapp.domain.model.CountryItem
import com.mckstudio.countriesapp.domain.repository.CountryRepository
import com.mckstudio.countriesapp.presentation.region_subregion.regionlist.state.RegionCountryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegionCountryListViewModel @Inject constructor(
    private val countryRepository: CountryRepository,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private var regionName = ""

    private var countryList = mutableStateListOf<CountryItem>()
    private var _state = MutableStateFlow(RegionCountryState())

    val countryListState: StateFlow<RegionCountryState> = _state

    init {
        savedStateHandle.get<String>(Constants.Region_Name)?.let {
            //retry butonuna tıkladığında region name yi tutmam gerektiği için kullandım
            regionName = it
            getCountryList(countryName = regionName)
        }
    }

    fun getCountryList(countryName: String? = null) = viewModelScope.launch {
        countryRepository.getCountryWithRegion(regionName = countryName ?: regionName).collectLatest { response ->
            when (response) {
                is Response.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true,
                            error = "",
                        )
                    }
                }

                is Response.Error -> {
                    _state.update {
                        it.copy(
                            loading = false,
                            error = response.message
                        )
                    }
                }

                is Response.Success -> {
                    val newData = response.data.map { countryItem ->
                        CountryItem(
                            flag = countryItem.flag,
                            name = countryItem.name,
                            capital = countryItem.capital,
                            countryDetailItem = countryItem.countryDetailItem
                        )
                    }

                    countryList += newData

                    _state.update {
                        it.copy(
                            loading = false,
                            error = "",
                            countryData = countryList
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
                countryData = emptyList()
            )
        }
    }
}