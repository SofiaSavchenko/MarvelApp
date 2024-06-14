package com.example.network.data.remote

import com.example.core.entity.ApiError
import com.example.core.entity.Either
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class EitherCallAdapter<R>(
    private val successType: Type
) : CallAdapter<R, Call<Either<ApiError, R>>> {

    override fun adapt(call: Call<R>): Call<Either<ApiError, R>> = EitherCall(call, successType)
    override fun responseType(): Type = successType
}
