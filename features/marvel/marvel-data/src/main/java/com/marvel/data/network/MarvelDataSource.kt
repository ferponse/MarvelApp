package com.marvel.data.network

import com.marvel.common_domain.model.ServiceErrorDomain
import com.marvel.data.model.CharactersResponseDTO
import com.marvel.util.Either

interface MarvelDataSource {

    fun getCharacters(
        offset: Int,
        limit: Int
    ): Either<CharactersResponseDTO, ServiceErrorDomain>

    fun getCharacterById(id: String): Either<CharactersResponseDTO, ServiceErrorDomain>

}