package com.marvel.ui.mapper

import com.marvel.domain.model.CharactersResponseDataResultInfoDomainModel
import com.marvel.domain.model.CharactersResponseDomainModel
import com.marvel.ui.model.*

fun CharactersResponseDomainModel.toCharactersResponseDataUIModel() =
    CharactersResponseData(
        total = this.data.total,
        characters = this.data.results.map { resultDomainModel ->
            CharacterUIModel(
                id = resultDomainModel.id,
                name = resultDomainModel.name,
                description = resultDomainModel.description,
                thumbnail = CharacterThumbnailUIModel(
                    thumbnailURL = resultDomainModel.thumbnail.thumbnailURL
                ),
                resourceURI = resultDomainModel.resourceURI,
                comics = resultDomainModel.comics.toCharactersResponseDataResultInfoUIModel(),
                series = resultDomainModel.series.toCharactersResponseDataResultInfoUIModel(),
                stories = resultDomainModel.stories.toCharactersResponseDataResultInfoUIModel(),
                events = resultDomainModel.events.toCharactersResponseDataResultInfoUIModel(),
            )
        }
    )

fun CharactersResponseDataResultInfoDomainModel.toCharactersResponseDataResultInfoUIModel() =
    CharacterInfoUIModel(
        available = this.available,
        collectionURI = this.collectionURI,
        items = this.items.map { itemDomainModel ->
            CharacterInfoItemUIModel(
                resourceURI = itemDomainModel.resourceURI,
                name = itemDomainModel.name
            )
        },
        returned = this.returned
    )
