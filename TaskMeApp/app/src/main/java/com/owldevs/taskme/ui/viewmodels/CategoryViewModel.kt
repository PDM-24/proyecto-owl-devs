package com.owldevs.taskme.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owldevs.taskme.data.api.ApiClient
import com.owldevs.taskme.data.api.CategoryApi
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {
    private val _categories = MutableLiveData<List<CategoryApi>>()
    val categories: LiveData<List<CategoryApi>> = _categories
    fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getCategories()
                _categories.value = response
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }
}