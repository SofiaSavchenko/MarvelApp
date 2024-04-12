package com.example.marvelapp.data.remote.model.either

import com.example.marvelapp.data.remote.ApiError
import com.example.marvelapp.data.remote.HttpError
import com.example.marvelapp.data.remote.NetworkError
import com.example.marvelapp.data.remote.UnknownApiError
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type

class EitherCall<R>(
    private val delegate: Call<R>,
    private val successType: Type
) : Call<Either<ApiError, R>> {

    override fun enqueue(callback: Callback<Either<ApiError, R>>) = delegate.enqueue(
        object : Callback<R> {

            override fun onResponse(call: Call<R>, response: Response<R>) {
                callback.onResponse(this@EitherCall, Response.success(response.toEither()))
            }

            private fun Response<R>.toEither(): Either<ApiError, R> {

                if (!isSuccessful) {
                    val errorBody = errorBody()?.string() ?: ""
                    return Either.Fail(HttpError(code(), errorBody))
                }

                body()?.let { body -> return Either.Success(body) }

                return if (successType == Unit::class.java) {
                    @Suppress("UNCHECKED_CAST")
                    Either.Success(Unit) as Either<ApiError, R>
                } else {
                    @Suppress("UNCHECKED_CAST")
                    Either.Fail(UnknownError("Response body was null")) as Either<ApiError, R>
                }
            }

            override fun onFailure(call: Call<R>, throwable: Throwable) {
                val error = when (throwable) {
                    is IOException -> NetworkError(throwable)
                    else -> UnknownApiError(throwable)
                }
                callback.onResponse(this@EitherCall, Response.success(Either.Fail(error)))
            }
        }
    )

    override fun clone(): Call<Either<ApiError, R>> = EitherCall(delegate.clone(), successType)

    override fun execute(): Response<Either<ApiError, R>> = throw UnsupportedOperationException()

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

}
