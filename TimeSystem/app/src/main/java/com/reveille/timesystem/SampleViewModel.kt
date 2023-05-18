package com.reveille.timesystem

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SampleViewModel:ViewModel() {
    private val apiService by lazy {
        createApiService()
    }

    private val _productList = MutableLiveData<List<ResponseModel>>()
    val productList: LiveData<List<ResponseModel>> = _productList


    fun getProfileInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val res = apiService.getProfile()
            Log.d("TAG", "getProfileInfo: $res")
        }
    }
}