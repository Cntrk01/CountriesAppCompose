package com.mckstudio.countriesapp.domain.model

import com.mckstudio.countriesapp.components.RecommendedCardModel

data class RecommendedItem(
    val countryImage : String?,
    val countryName : String,
    val countryCapital : String?,
    val countryRegion : String?,
)

fun RecommendedItem.toRecommendedCardModel() : RecommendedCardModel{
    return RecommendedCardModel(
        countryName = countryName,
        countryCapital = countryCapital ?: "",
        countryRegion = countryRegion ?: "",
        countryImageUrl = countryImage ?: "",
    )
}

fun List<RecommendedItem>.toRecommendedCardModelList() : List<RecommendedCardModel>{
    return this.map { it.toRecommendedCardModel() }
}