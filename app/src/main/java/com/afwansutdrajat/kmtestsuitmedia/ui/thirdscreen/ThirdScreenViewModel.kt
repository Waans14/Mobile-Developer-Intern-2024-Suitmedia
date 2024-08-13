package com.afwansutdrajat.kmtestsuitmedia.ui.thirdscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.afwansutdrajat.kmtestsuitmedia.data.remote.response.DataItem
import com.afwansutdrajat.kmtestsuitmedia.data.repository.UserRepository

class ThirdScreenViewModel(private val repository: UserRepository) : ViewModel() {

    fun getUser(): LiveData<PagingData<DataItem>> {
        return repository.getUser().cachedIn(viewModelScope)
    }

}
