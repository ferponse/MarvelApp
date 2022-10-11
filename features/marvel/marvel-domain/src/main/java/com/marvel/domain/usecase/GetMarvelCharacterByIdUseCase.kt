package com.marvel.domain.usecase

import com.marvel.common_domain.model.ServiceErrorDomain
import com.marvel.domain.model.CharactersResponseDomainModel
import com.marvel.domain.repository.MarvelRepository
import com.marvel.util.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMarvelCharacterByIdUseCase @Inject constructor(
    private val repository: MarvelRepository
) {

    operator fun invoke(id: String): Flow<Either<CharactersResponseDomainModel, ServiceErrorDomain>> = flow {
        emit(repository.getCharacterById(id = id))
    }

}