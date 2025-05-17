package com.kkh.single.module.template.data.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun <T> performApiCall(
    funcName: String,
    apiCall: suspend () -> Response<T>
): Flow<Result<T>> = flow {
    try {
        val response = apiCall()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(Result.success(it))
            } ?: emit(Result.failure(Exception("[$funcName] - response.body()가 null입니다")))
        } else {
            val errorBody = response.errorBody()?.string() ?: "${response.errorBody()?.string()}"
            emit(Result.failure(Exception(errorBody)))
        }
    } catch (e: Exception) {
        emit(Result.failure(Exception("[$funcName] - catch에 잡힌 에러 : ${e.message}")))
    }
}