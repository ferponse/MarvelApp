package com.marvel.ui.mapper

import com.marvel.domain.model.CharactersResponseDataResultInfoDomainModel
import com.marvel.domain.model.CharactersResponseDataResultInfoItemDomainModel
import com.marvel.ui.model.*
import org.junit.Test
import kotlin.test.assertEquals

class MarvelModelUIMapper {
    private val charactersResponseDataResultInfoDomainModel =
        CharactersResponseDataResultInfoDomainModel(
            available = 1,
            collectionURI = "https://fakeCollectionUri.com/5784y3",
            items = listOf(
                CharactersResponseDataResultInfoItemDomainModel(
                    resourceURI = "https://fakeResourceUri.com/748543",
                    name = "Resource name"
                )
            ),
            returned = 1
        )

    private val charactersResponseDomainModel =
        com.marvel.domain.model.CharactersResponseDomainModel(
            data = com.marvel.domain.model.CharactersResponseDataDomainModel(
                results = listOf(
                    com.marvel.domain.model.CharactersResponseDataResultDomainModel(
                        id = 1,
                        name = "Spiderman",
                        description = "Good film",
                        thumbnail = com.marvel.domain.model.CharactersResponseDataResultThumbnailDomainModel(
                            thumbnailURL = "https://fakeUrlImage.com/resourceImage.png"
                        ),
                        resourceURI = "https://fakeResourceUri.com/748543",
                        comics = charactersResponseDataResultInfoDomainModel,
                        series = charactersResponseDataResultInfoDomainModel,
                        stories = charactersResponseDataResultInfoDomainModel,
                        events = charactersResponseDataResultInfoDomainModel
                    )
                ),
                total = 1
            )
        )

    private val characterInfoUIModel =
        CharacterInfoUIModel(
            available = 1,
            collectionURI = "https://fakeCollectionUri.com/5784y3",
            items = listOf(
                CharacterInfoItemUIModel(
                    resourceURI = "https://fakeResourceUri.com/748543",
                    name = "Resource name"
                )
            ),
            returned = 1
        )

    private val characterUIModel =
        CharacterUIModel(
            id = 1,
            name = "Spiderman",
            description = "Good film",
            thumbnail = CharacterThumbnailUIModel(
                thumbnailURL = "https://fakeUrlImage.com/resourceImage.png"
            ),
            resourceURI = "https://fakeResourceUri.com/748543",
            comics = characterInfoUIModel,
            series = characterInfoUIModel,
            stories = characterInfoUIModel,
            events = characterInfoUIModel
        )

    @Test
    fun `toCharactersResponseUIModel() mapper test`() {
        val resultUIModel = charactersResponseDomainModel.toCharactersResponseDataUIModel()
        assertEquals(1, resultUIModel.total)
        assertEquals(characterUIModel, resultUIModel.characters.first())
    }

    @Test
    fun `toCharactersResponseDataResultInfoUIModel() mapper test`() {
        assertEquals(
            characterInfoUIModel,
            charactersResponseDataResultInfoDomainModel.toCharactersResponseDataResultInfoUIModel()
        )
    }
}