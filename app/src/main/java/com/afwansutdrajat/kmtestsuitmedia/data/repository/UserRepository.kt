package com.afwansutdrajat.kmtestsuitmedia.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.afwansutdrajat.kmtestsuitmedia.data.remote.response.DataItem
import com.afwansutdrajat.kmtestsuitmedia.data.remote.retrofit.ApiService
import com.afwansutdrajat.kmtestsuitmedia.data.repository.source.UserPagingSource

class UserRepository private constructor(
    private val apiService: ApiService,
){

    fun getUser(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 2
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(
            apiService: ApiService,
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService)
            }.also { instance = it }
    }
}