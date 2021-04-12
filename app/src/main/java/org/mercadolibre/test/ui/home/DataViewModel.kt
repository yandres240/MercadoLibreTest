package org.mercadolibre.test.ui.home

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mercadolibre.test.data.repository.SearchRepository

class DataViewModel(private val searchRepository: SearchRepository): ViewModel() {

    private val results = MutableLiveData<org.mercadolibre.test.utils.Result<ArrayList<org.mercadolibre.test.data.model.Result>>>()
    private var currentPage = 1
    var listState: Parcelable? = null

    fun getItems() = results

    fun getCurrentPage() = currentPage

    fun loadData(query: String) {
        try {
            if (currentPage == 1) {
                results.postValue(org.mercadolibre.test.utils.Result.InProgress)
            }
            viewModelScope.launch(Dispatchers.IO) {
                val response = searchRepository.getDataFromApi(query)
                response?.let {
                    val resultsList = it.body()?.results
                    resultsList?.let { list ->
                        if (currentPage == 1) { //set photos for first page
                            results.postValue(org.mercadolibre.test.utils.Result.Success(list))
                        } else { //add photos to current list
                            val currentPhotos: ArrayList<org.mercadolibre.test.data.model.Result>? = results.value?.extractData
                            if (currentPhotos == null || currentPhotos.isEmpty()) {
                                results.postValue(org.mercadolibre.test.utils.Result.Success(list))
                            } else {
                                currentPhotos.addAll(list)
                                results.postValue(org.mercadolibre.test.utils.Result.Success(currentPhotos))
                            }
                        }
                    } ?: run {
                        results.postValue(org.mercadolibre.test.utils.Result.Success(arrayListOf()))
                    }
                } ?: run {
                    results.postValue(org.mercadolibre.test.utils.Result.Success(arrayListOf()))
                }
            }
        } catch (error: Exception) {
            results.postValue(org.mercadolibre.test.utils.Result.Error(error))
        }
    }

    fun loadDataNextPage(query: String) {
        currentPage++
        loadData(query)
    }
}