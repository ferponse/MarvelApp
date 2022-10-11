package com.marvel.data.mapper

import com.marvel.data.model.*
import com.marvel.domain.model.*
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class MarvelModelDataMapperTest {

    private val charactersResponseDataResultInfoDTO =
        CharactersResponseDataResultInfoDTO(
            available = 1,
            collectionURI = "https://fakeCollectionUri.com/5784y3",
            items = listOf(
                CharactersResponseDataResultInfoItemDTO(
                    resourceURI = "https://fakeResourceUri.com/748543",
                    name = "Resource name"
                )
            ),
            returned = 1
        )

    private val charactersResponseDTO = CharactersResponseDTO(
        data = CharactersResponseDataDTO(
            total = 1,
            results = listOf(
                CharactersResponseDataResultDTO(
                    id = 1,
                    name = "Spiderman",
                    description = "Good film",
                    thumbnail = CharactersResponseDataResultThumbnailDTO(
                        path = "https://fakeUrlImage.com/resourceImage",
                        extension = ".png"
                    ),
                    resourceURI = "https://fakeResourceUri.com/748543",
                    comics = charactersResponseDataResultInfoDTO,
                    series = charactersResponseDataResultInfoDTO,
                    stories = charactersResponseDataResultInfoDTO,
                    events = charactersResponseDataResultInfoDTO
                )
            )
        )
    )

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
        CharactersResponseDomainModel(
            data = CharactersResponseDataDomainModel(
                total = 1,
                results = listOf(
                    CharactersResponseDataResultDomainModel(
                        id = 1,
                        name = "Spiderman",
                        description = "Good film",
                        thumbnail = CharactersResponseDataResultThumbnailDomainModel(
                            thumbnailURL = "https://fakeUrlImage.com/resourceImage.png"
                        ),
                        resourceURI = "https://fakeResourceUri.com/748543",
                        comics = charactersResponseDataResultInfoDomainModel,
                        series = charactersResponseDataResultInfoDomainModel,
                        stories = charactersResponseDataResultInfoDomainModel,
                        events = charactersResponseDataResultInfoDomainModel
                    )
                )
            )
        )

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `toCharactersResponseDomainModel() mapper test`() {
        assertEquals(
            charactersResponseDomainModel,
            charactersResponseDTO.toCharactersResponseDomainModel()
        )
    }

    @Test
    fun `toCharactersResponseDataResultInfoDomainModel() mapper test`() {
        assertEquals(
            charactersResponseDataResultInfoDomainModel,
            charactersResponseDataResultInfoDTO.toCharactersResponseDataResultInfoDomainModel()
        )
    }

}