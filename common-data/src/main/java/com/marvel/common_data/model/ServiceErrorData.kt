package com.marvel.common_data.model

open class ServiceErrorData(
    val description: String?,
    val statusCode: Int,
    val headers: Map<String, List<String>>,
)