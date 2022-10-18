package com.marvel.domain.usecase

import app.cash.turbine.test
import com.marvel.domain.model.CharactersResponseDataResultInfoDomainModel
import com.marvel.domain.model.CharactersResponseDataResultInfoItemDomainModel
import com.marvel.domain.repository.MarvelRepository
import com.marvel.test.MainCoroutineRule
import com.marvel.util.buildSuccess
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.properties.Delegates
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@RunWith(JUnit4::class)
internal class GetMarvelCharactersUseCaseTest {
    @get:Rule
    val coroutineTestRule = MainCoroutineRule()


    private val marvelRepository = mockk<MarvelRepository> {}

    private var useCase: GetMarvelCharactersUseCase by Delegates.notNull()

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

    @Before
    fun setUp() {
        useCase = GetMarvelCharactersUseCase(marvelRepository)
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `FinancialHealthRecategorizeUseCase returns success`() = coroutineTestRule.runTest {
        every { marvelRepository.getCharacters(any()) } returns buildSuccess(charactersResponseDomainModel)

        useCase(0).test {
            assertEquals(buildSuccess(charactersResponseDomainModel), awaitItem())
            awaitComplete()
        }

        verify(exactly = 1) { marvelRepository.getCharacters(any()) }
        confirmVerified()
    }

}