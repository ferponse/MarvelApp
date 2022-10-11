package com.marvel.common_data.model

open class ServiceResponseData<T>(
    val optData: T?,
    val statusCode: Int,
    val headers: Map<String, List<String>>,
) {
    val data: T
        get() = optData!!
}