package com.reveille.timesystem


interface ApiService {
    suspend fun getProfile(): List<ResponseModel>
}