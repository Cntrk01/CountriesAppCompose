package com.mckstudio.countriesapp.data

import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.SocketTimeoutException

sealed class BaseError{
    companion object{
        fun errMessage(throwable: Throwable) : String {
            return when(throwable){
                is SocketTimeoutException -> "Timeout.Try Again"
                is IOException -> "Couldn't reach server.Check your internet connection.."
                is HttpException -> {
                    mapHttpError(throwable)
                }
                is Exception -> "An unexpected error occured"
                else -> "Unknown Error"
            }
        }

        private fun mapHttpError(e: HttpException): String {
            return when (e.code()) {
                400 -> "Bad request"
                401 -> "Unauthorized"
                403 -> "Forbidden"
                404 -> "Country not found"
                500 -> "Server error"
                else -> "HTTP ${e.code()} error"
            }
        }
    }
}