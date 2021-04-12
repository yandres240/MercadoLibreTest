package org.mercadolibre.test.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(@SerializedName("results") val results: ArrayList<Result>)