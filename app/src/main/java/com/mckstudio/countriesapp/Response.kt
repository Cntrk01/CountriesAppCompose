package com.mckstudio.countriesapp

import com.mckstudio.countriesapp.data.BaseError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

sealed class Response<out T>(val data: T? = null, val message:String? = null) {
    class Success<T>(data: T) : Response<T>(data = data)
    class Error(message: String) : Response<Nothing>(message=message)
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