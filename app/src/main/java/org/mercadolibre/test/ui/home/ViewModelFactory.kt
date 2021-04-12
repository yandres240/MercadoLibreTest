package org.mercadolibre.test.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.mercadolibre.test.data.api.SearchService
import org.mercadolibre.test.data.repository.SearchRepository

class ViewModelFactory(private val searchService: SearchService) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DataViewModel::class.java)) {
            return DataViewModel(
                SearchRepository(searchService)
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}