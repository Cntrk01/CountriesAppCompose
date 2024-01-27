package com.example.countriesapp.presentation.country_list.viewmodel

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
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
    private var countryList = mutableStateListOf<CountryItem>()
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
                    PAGE_SIZE++
                }
            }
        }
    }
}