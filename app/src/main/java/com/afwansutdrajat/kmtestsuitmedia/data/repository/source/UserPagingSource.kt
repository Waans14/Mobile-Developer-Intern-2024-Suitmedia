package com.afwansutdrajat.kmtestsuitmedia.data.repository.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.afwansutdrajat.kmtestsuitmedia.data.remote.response.DataItem
import com.afwansutdrajat.kmtestsuitmedia.data.remote.response.ResponseUser
import com.afwansutdrajat.kmtestsuitmedia.data.remote.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UserPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, DataItem>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val responseData = fetchDataAsynchronously(page)
            LoadResult.Page(
                data = responseData,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (responseData.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private suspend fun fetchDataAsynchronously( position: Int): List<DataItem> {
        return suspendCoroutine { continuation ->
            apiService.getUsers(position).enqueue(object :
                Callback<ResponseUser> {
                override fun onResponse(
                    call: Call<ResponseUser>,
                    response: Response<ResponseUser>
                ) {
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        val userList = responseData?.data.orEmpty()
                        continuation.resume(userList as List<DataItem>)
                    } else {
                        continuation.resumeWithException(Exception("API request failed"))
                    }
                }

                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}