package org.mercadolibre.test.data.repository

import android.util.Log
import org.mercadolibre.test.BuildConfig
import org.mercadolibre.test.data.api.SearchService
import org.mercadolibre.test.data.model.ItemsQuery
import org.mercadolibre.test.data.model.SearchResponse
import retrofit2.Response

class SearchRepository(val apiService: SearchService) {

    private val TAG = SearchRepository::class.java.name
    suspend fun getDataFromApi(query: String): Response<SearchResponse>? {
        try {
            val response = apiService.getData(query)
            response?.let {
                return it
            }
        } catch (error: Exception) {
            Log.e(TAG, "Error: ${error.message}")
            return null
        }
        return null
    }

}