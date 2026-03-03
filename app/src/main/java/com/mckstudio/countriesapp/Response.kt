package com.mckstudio.countriesapp

import com.mckstudio.countriesapp.data.BaseError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

sealed class Response<out T> {
    data class Success<T>(val data: T) : Response<T>()
    data class Error(val message: String) : Response<Nothing>()
    object Loading : Response<Nothing>()

    companion object {
        fun <T> safeOperation(
            block: suspend () -> T
        ): Flow<Response<T>> = flow {
            emit(Loading)
            try {
                val result = block()
                emit(Success(result))
            } catch (e: Exception) {
                emit(Error(message = BaseError.errMessage(e)))
            }
        }

        fun <T> safeOperationWithCollector(
            block: suspend FlowCollector<Response<T>>.() -> Unit
        ): Flow<Response<T>> = flow {
            emit(Loading)
            try {
                block()
            } catch (e: Exception) {
                emit(Error(BaseError.errMessage(e)))
            }
        }
    }
}