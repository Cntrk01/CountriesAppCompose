package com.example.countriesapp.presentation.region_subregion.regionlist.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesapp.common.Constants
import com.example.countriesapp.data.response.Response
import com.example.countriesapp.domain.model.CountryItem
import com.example.countriesapp.domain.use_case.CountryRegionUseCase
import com.example.countriesapp.presentation.region_subregion.regionlist.state.RegionCountryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegionCountryListViewModel @Inject constructor(private val countryRegionUseCase: CountryRegionUseCase,
    private val savedStateHandle: SavedStateHandle) :
    ViewModel() {

    private var regionName=""

    private var countryList = mutableStateListOf<CountryItem>()
    private var _state = MutableStateFlow(RegionCountryState())

    val countryListState: StateFlow<RegionCountryState> = _state

    init {
        getCountryList()

        savedStateHandle.get<String>(Constants.REGION_NAME)?.let {
            getCountryList(countryName = it)
        }
    }

    fun getCountryList(countryName:String?=null) = viewModelScope.launch {
        countryName?.let {countryNAME->
            countryRegionUseCase(regionName = countryNAME).collectLatest { response ->
                when (response) {
                    is Response.Loading -> {
                        _state.update {
                            it.copy(
                                loading = true,
                                error="",
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
                        val newData = response.data?.map { countryItem ->
                            CountryItem(
                                flag = countryItem.flag,
                                name = countryItem.name,
                                countryDetailItem = countryItem.countryDetailItem
                            )
                        } ?: emptyList() //null geleceği için böyle yaptım ? var datadan önce

                        countryList +=newData

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
    }
}