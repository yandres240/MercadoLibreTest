package org.mercadolibre.test.data.api

import org.mercadolibre.test.data.model.ItemsQuery
import org.mercadolibre.test.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("search")
    suspend fun getData(@Query("q") query: String): Response<SearchResponse>?
}