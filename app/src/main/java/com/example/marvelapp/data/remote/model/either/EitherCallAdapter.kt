package com.example.marvelapp.data.remote.model.either

import com.example.marvelapp.data.remote.ApiError
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class EitherCallAdapter<R>(
    private val successType: Type
) : CallAdapter<R, Call<Either<ApiError, R>>> {

    override fun adapt(call: Call<R>): Call<Either<ApiError, R>> = EitherCall(call, successType)

    override fun responseType(): Type = successType
}