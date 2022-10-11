package com.marvel.domain.repository

import com.marvel.common_domain.model.ServiceErrorDomain
import com.marvel.domain.model.CharactersResponseDomainModel
import com.marvel.util.Either

interface MarvelRepository {

    fun getCharacters(offset: Int): Either<CharactersResponseDomainModel, ServiceErrorDomain>

    fun getCharacterById(id: String): Either<CharactersResponseDomainModel, ServiceErrorDomain>

}