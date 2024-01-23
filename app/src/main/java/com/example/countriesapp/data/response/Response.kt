package com.example.countriesapp.data.response

sealed class Response<T>(val data: T? = null, val message:String? = null) {
    class Success<T>(data: T) : com.example.countriesapp.data.response.Response<T>(data = data)
    class Error<T>(message: String, data:T? = null) : com.example.countriesapp.data.response.Response<T>(data = data,message=message)
    class Loading<T>(data:T?= null) : com.example.countriesapp.data.response.Response<T>(data=data)
}