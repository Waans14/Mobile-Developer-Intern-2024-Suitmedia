package com.afwansutdrajat.kmtestsuitmedia.data.remote.retrofit

import com.afwansutdrajat.kmtestsuitmedia.data.remote.response.ResponseUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 10
    ): Call<ResponseUser>

}