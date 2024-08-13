package com.afwansutdrajat.kmtestsuitmedia.data.di

import android.content.Context
import com.afwansutdrajat.kmtestsuitmedia.data.remote.retrofit.ApiConfig
import com.afwansutdrajat.kmtestsuitmedia.data.repository.UserRepository

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }
}