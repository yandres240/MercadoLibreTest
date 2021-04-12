package org.mercadolibre.test.data.api

import okhttp3.OkHttpClient
import org.mercadolibre.test.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    var client: OkHttpClient = OkHttpClient.Builder().build()

    private var retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).client(client).build()

    fun <T> createService(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}