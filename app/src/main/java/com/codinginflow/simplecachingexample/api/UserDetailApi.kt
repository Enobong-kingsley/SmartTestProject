package com.codinginflow.simplecachingexample.api

import com.codinginflow.simplecachingexample.data.SlugCategoryResponse
import com.codinginflow.simplecachingexample.data.UserDetail
import retrofit2.http.GET

interface UserDetailApi {
    companion object{
        const val BASE_URL = "https://cupidapi.smartflowtech.org/api/"
    }
    @GET("v1/ntwp/get_categories")
    suspend fun getUserDetails(): SlugCategoryResponse
}