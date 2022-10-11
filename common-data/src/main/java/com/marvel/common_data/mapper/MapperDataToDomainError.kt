package com.marvel.common_data.mapper

import com.marvel.common_data.model.ServiceErrorData
import com.marvel.common_domain.model.ServiceErrorDomain

fun ServiceErrorData.toDomainError() =
    if (this.statusCode in 400..499) {
        ServiceErrorDomain.BAD_REQUEST
    } else if (this.statusCode in 500 .. 599) {
        ServiceErrorDomain.INTERNAL_SERVER_ERROR
    } else {
        ServiceErrorDomain.OTHER
    }