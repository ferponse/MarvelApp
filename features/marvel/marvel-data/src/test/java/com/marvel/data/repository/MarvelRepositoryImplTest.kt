package com.marvel.data.repository

import com.marvel.common_domain.model.ServiceErrorDomain
import com.marvel.data.network.MarvelDataSource
import com.marvel.data.persistence.MarvelPersistence
import com.marvel.domain.repository.MarvelRepository
import com.marvel.util.buildFailure
import com.marvel.util.buildSuccess
import com.marvel.util.isSuccess
import io.mockk.*
import org.junit.Before
import org.junit.Test

class MarvelRepositoryImplTest {
    private lateinit var repository: MarvelRepository
    private val marvelDataSource = mockk<MarvelDataSource>()
    private val marvelPersistence = mockk<MarvelPersistence>()

    private val charactersResponseDTO = com.marvel.data.model.CharactersResponseDTO(
        data = com.marvel.data.model.CharactersResponseDataDTO(
            total = 1,
            results = listOf(
                com.marvel.data.model.CharactersResponseDataResultDTO(
                    id = 1,
                    name = "Spiderman",
                    description = "Good film",
                    thumbnail = com.marvel.data.model.CharactersResponseDataResultThumbnailDTO(
                        path = "https://fakeUrlImage.com/resourceImage",
                        extension = "png"
                    ),
                    resourceURI = "https://fakeResourceUri.com/748543",
                    comics = com.marvel.data.model.CharactersResponseDataResultInfoDTO(
                        available = 1,
                        collectionURI = "https://fakeCollectionUri.com/5784y3",
                        items = listOf(
                            com.marvel.data.model.CharactersResponseDataResultInfoItemDTO(
                                resourceURI = "https://fakeResourceUri.com/748543",
                                name = "Resource name"
                            )
                        ),
                        returned = 1
                    ),
                    series = com.marvel.data.model.CharactersResponseDataResultInfoDTO(
                        available = 1,
                        collectionURI = "https://fakeCollectionUri.com/5784y3",
                        items = listOf(
                            com.marvel.data.model.CharactersResponseDataResultInfoItemDTO(
                                resourceURI = "https://fakeResourceUri.com/748543",
                                name = "Resource name"
                            )
                        ),
                        returned = 1
                    ),
                    stories = com.marvel.data.model.CharactersResponseDataResultInfoDTO(
                        available = 1,
                        collectionURI = "https://fakeCollectionUri.com/5784y3",
                        items = listOf(
                            com.marvel.data.model.CharactersResponseDataResultInfoItemDTO(
                                resourceURI = "https://fakeResourceUri.com/748543",
                                name = "Resource name"
                            )
                        ),
                        returned = 1
                    ),
                    events = com.marvel.data.model.CharactersResponseDataResultInfoDTO(
                        available = 1,
                        collectionURI = "https://fakeCollectionUri.com/5784y3",
                        items = listOf(
                            com.marvel.data.model.CharactersResponseDataResultInfoItemDTO(
                                resourceURI = "https://fakeResourceUri.com/748543",
                                name = "Resource name"
                            )
                        ),
                        returned = 1
                    )
                )
            )
        )
    )

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        repository = MarvelRepositoryImpl(
            marvelDataSource = marvelDataSource,
            marvelPersistence = marvelPersistence
        )
    }

    @Test
    fun `call getCharacters returns success`() {
        every { marvelDataSource.getCharacters(any(), any()) } returns (buildSuccess(charactersResponseDTO))

        val response = repository.getCharacters(1)

        assert(response.isSuccess())

        verify(exactly = 1) { marvelDataSource.getCharacters(any(), any()) }
        confirmVerified(marvelDataSource)
    }

    @Test
    fun `call getCharacters returns failure`() {
        every { marvelDataSource.getCharacters(any(), any()) } returns (buildFailure(ServiceErrorDomain.BAD_REQUEST))

        val response = repository.getCharacters(1)

        assert(!response.isSuccess())

        verify(exactly = 1) { marvelDataSource.getCharacters(any(), any()) }
        confirmVerified(marvelDataSource)
    }

    @Test
    fun `call getCharacterById returns success`() {
        every { marvelDataSource.getCharacterById("748543") } returns (buildSuccess(
            charactersResponseDTO
        ))

        val response = repository.getCharacterById("748543")

        assert(response.isSuccess())

        verify(exactly = 1) { marvelDataSource.getCharacterById(any()) }
        confirmVerified(marvelDataSource)
    }

    @Test
    fun `call getCharacterById returns failure`() {
        every { marvelDataSource.getCharacterById("748543") } returns (buildFailure(ServiceErrorDomain.BAD_REQUEST))

        val response = repository.getCharacterById("748543")

        assert(!response.isSuccess())

        verify(exactly = 1) { marvelDataSource.getCharacterById(any()) }
        confirmVerified(marvelDataSource)
    }
}