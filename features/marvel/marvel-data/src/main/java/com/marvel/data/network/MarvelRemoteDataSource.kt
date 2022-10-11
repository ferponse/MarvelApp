package com.marvel.data.network

// import com.example.marvelapp.BuildConfig
import com.marvel.common_data.mapper.toDomainError
import com.marvel.common_domain.model.ServiceErrorDomain
import com.marvel.data.model.CharactersResponseDTO
import com.marvel.data.utils.RetrofitServiceExecutor
import com.marvel.util.*
import javax.inject.Inject

class MarvelRemoteDataSource @Inject constructor(
    private val executor: RetrofitServiceExecutor,
    private val marvelApiClient: MarvelApiClient,
    private val servicesUrlProvider: ServicesUrlProvider
) : MarvelDataSource {

    override fun getCharacters(
        offset: Int,
        limit: Int
    ): Either<CharactersResponseDTO, ServiceErrorDomain> {
        val call = marvelApiClient.getCharacters(
            url = servicesUrlProvider.getMarvelCharactersUrl(),
            ts = 1,
            apiKey = "35f8db76283c6ac26e1ab2b37f45e795", //BuildConfig.MARVEL_API_PUBLIC_KEY,
            hash = "6fed11cbd517a0da9fa9c9a145b74b81", //BuildConfig.MARVEL_API_HASH
            offset = offset,
            limit = limit
        )
        val response = executor.performCall(call)
        return if (response.isSuccess()) {
            buildSuccess(response.data.data)
        } else {
            buildFailure(response.error.toDomainError())
        }
    }

    override fun getCharacterById(id: String): Either<CharactersResponseDTO, ServiceErrorDomain> {
        val call = marvelApiClient.getCharacterById(
            url = servicesUrlProvider.getMarvelCharacterByIdUrl(id = id),
            ts = 1,
            apiKey = "", //BuildConfig.MARVEL_API_PUBLIC_KEY,
            hash = "", //BuildConfig.MARVEL_API_HASH
        )
        val response = executor.performCall(call)
        return if (response.isSuccess()) {
            buildSuccess(response.data.data)
        } else {
            buildFailure(response.error.toDomainError())
        }
    }

}