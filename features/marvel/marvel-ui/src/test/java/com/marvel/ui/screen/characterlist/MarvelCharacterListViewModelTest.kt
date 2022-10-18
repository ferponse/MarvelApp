package com.marvel.ui.screen.characterlist

import app.cash.turbine.test
import com.marvel.domain.model.*
import com.marvel.domain.usecase.GetMarvelCharactersUseCase
import com.marvel.test.MainCoroutineRule
import com.marvel.util.buildSuccess
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.properties.Delegates
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@RunWith(JUnit4::class)
internal class MarvelCharacterListViewModelTest {

    @get:Rule
    val coroutineTestRule = MainCoroutineRule()

    private val getMarvelCharactersUseCase = mockk<GetMarvelCharactersUseCase>()
    private val viewModelHelper = mockk<MarvelCharacterListViewModelHelper>()

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
                ),
                total = 1
            )
        )

    private var viewModel: MarvelCharacterListViewModel by Delegates.notNull()

    @Before
    fun setUp() {
        viewModel = MarvelCharacterListViewModel(viewModelHelper, getMarvelCharactersUseCase)
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `getMarvelCharactersFirstPage() with data that doesn't need pagination test`() = coroutineTestRule.runTest {
        every { getMarvelCharactersUseCase(any()) } returns flowOf(buildSuccess(charactersResponseDomainModel))

        viewModel.state.test state@ {
            viewModel.event.test event@ {
                viewModel.getMarvelCharactersFirstPage()

                assertEquals(MarvelCharacterListViewModel.Event.GetCharactersFinishPaging, this@event.awaitItem())
            }
            assertEquals(MarvelCharacterListViewModel.State.IdleCharactersFirstPage, this@state.awaitItem())
            assertEquals(MarvelCharacterListViewModel.State.LoadingCharactersFirstPage, this@state.awaitItem())
            assertEquals(MarvelCharacterListViewModel.State.IdleCharactersFirstPage, this@state.awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

}