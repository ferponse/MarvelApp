package com.marvel.data.repository

import com.marvel.common_domain.model.ServiceErrorDomain
import com.marvel.data.mapper.toCharactersResponseDomainModel
import com.marvel.data.network.MarvelDataSource
import com.marvel.data.persistence.MarvelPersistence
import com.marvel.domain.model.CharactersResponseDomainModel
import com.marvel.domain.repository.MarvelRepository
import com.marvel.util.*
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val marvelDataSource: MarvelDataSource,
    private val marvelPersistence: MarvelPersistence
): MarvelRepository {

    companion object {
        const val CHARACTERS_PAGINATION_LIMIT = 25
    }

    override fun getCharacters(offset: Int): Either<CharactersResponseDomainModel, ServiceErrorDomain> {
        val response = marvelDataSource.getCharacters(
            offset = offset,
            limit = CHARACTERS_PAGINATION_LIMIT
        )
        return if (response.isSuccess()) {
            buildSuccess(response.data.toCharactersResponseDomainModel())
        } else {
            buildFailure(response.error)
        }
    }

    override fun getCharacterById(id: String): Either<CharactersResponseDomainModel, ServiceErrorDomain> {
        val response = marvelDataSource.getCharacterById(id)
        return if (response.isSuccess()) {
            buildSuccess(response.data.toCharactersResponseDomainModel())
        } else {
            buildFailure(response.error)
        }
    }

}