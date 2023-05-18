package com.reveille.timesystem

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class ApiServiceImpl(private  val mHttpClient:HttpClient): ApiService {

    override suspend fun getProfile(): List<ResponseModel> =mHttpClient.get(PROFILE)

    companion object
    {
        private const val BASE_URL = "https://fakestoreapi.com"
        const val PROFILE = "$BASE_URL/products"
    }



}