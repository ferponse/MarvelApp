package com.marvel.data.mapper

import com.marvel.data.model.CharactersResponseDTO
import com.marvel.data.model.CharactersResponseDataResultInfoDTO
import com.marvel.domain.model.*

fun CharactersResponseDTO.toCharactersResponseDomainModel() =
    CharactersResponseDomainModel(
        data = CharactersResponseDataDomainModel(
            total = this.data.total,
            results = this.data.results.map { resultDTO ->
                CharactersResponseDataResultDomainModel(
                    id = resultDTO.id,
                    name = resultDTO.name,
                    description = resultDTO.description,
                    thumbnail = CharactersResponseDataResultThumbnailDomainModel(
                        thumbnailURL = "${resultDTO.thumbnail.path}.${resultDTO.thumbnail.extension}",
                    ),
                    resourceURI = resultDTO.resourceURI,
                    comics = resultDTO.comics.toCharactersResponseDataResultInfoDomainModel(),
                    series = resultDTO.series.toCharactersResponseDataResultInfoDomainModel(),
                    stories = resultDTO.stories.toCharactersResponseDataResultInfoDomainModel(),
                    events = resultDTO.events.toCharactersResponseDataResultInfoDomainModel(),
                )
            }
        )
    )


fun CharactersResponseDataResultInfoDTO.toCharactersResponseDataResultInfoDomainModel() =
    CharactersResponseDataResultInfoDomainModel(
        available = this.available,
        collectionURI = this.collectionURI,
        items = this.items.map { itemDTO ->
            CharactersResponseDataResultInfoItemDomainModel(
                resourceURI = itemDTO.resourceURI,
                name = itemDTO.name
            )
        },
        returned = this.returned
    )