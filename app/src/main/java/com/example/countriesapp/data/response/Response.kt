package com.example.countriesapp.data.response

sealed class Response<T>(val data: T? = null, val message:String? = null) {
    class Success<T>(data: T) : Response<T>(data = data)
    class Error<T>(message: String, data:T? = null) : Response<T>(data = data,message=message)
    class Loading<T>(data:T?= null) : Response<T>(data=data)
}