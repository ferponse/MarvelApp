package com.marvel.domain.model

data class CharactersResponseDomainModel(
    val data: CharactersResponseDataDomainModel
)

data class CharactersResponseDataDomainModel(
    val total: Int,
    val results: List<CharactersResponseDataResultDomainModel>
)

data class CharactersResponseDataResultDomainModel(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: CharactersResponseDataResultThumbnailDomainModel,
    val resourceURI: String,
    val comics: CharactersResponseDataResultInfoDomainModel,
    val series: CharactersResponseDataResultInfoDomainModel,
    val stories: CharactersResponseDataResultInfoDomainModel,
    val events: CharactersResponseDataResultInfoDomainModel
)

data class CharactersResponseDataResultThumbnailDomainModel(
    val thumbnailURL: String
)

data class CharactersResponseDataResultInfoDomainModel(
    val available: Int,
    val collectionURI: String,
    val items: List<CharactersResponseDataResultInfoItemDomainModel>,
    val returned: Int
)

data class CharactersResponseDataResultInfoItemDomainModel(
    val resourceURI: String,
    val name: String
)
