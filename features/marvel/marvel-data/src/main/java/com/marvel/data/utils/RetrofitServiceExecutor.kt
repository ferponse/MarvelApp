package com.marvel.data.utils

import com.google.gson.JsonParseException
import com.marvel.common_data.model.ServiceErrorData
import com.marvel.common_data.model.ServiceResponseData
import com.marvel.util.*
import retrofit2.Call
import java.io.IOException
import javax.inject.Inject

class RetrofitServiceExecutor @Inject constructor() {

    fun <T> performCall(call: Call<T>): Either<ServiceResponseData<T>, ServiceErrorData> {
        try {
            val response = call.execute()
            if (response.isSuccessful) {
                return buildSuccess(
                    ServiceResponseData(
                        optData = response.body(),
                        statusCode = response.code(),
                        headers = response.headers().toMultimap()
                    )
                )
            } else {
                return buildFailure(
                    ServiceErrorData(
                        description = response.errorBody()?.string(),
                        statusCode = response.code(),
                        headers = response.headers().toMultimap()
                    )
                )
            }
        } catch (ex: JsonParseException) {
            throw java.lang.Exception("URL: ${call.request().url()}\nMessage: ${ex.message}")
        } catch (ex: IOException) {
            throw java.lang.Exception("URL: ${call.request().url()}\nMessage: ${ex.message}")
        }
    }

}
