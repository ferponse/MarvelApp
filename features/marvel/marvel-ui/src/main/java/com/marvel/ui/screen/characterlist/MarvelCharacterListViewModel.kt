package com.marvel.ui.screen.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marvel.common_domain.model.ServiceErrorDomain
import com.marvel.domain.usecase.GetMarvelCharactersUseCase
import com.marvel.ui.mapper.toCharactersResponseDataUIModel
import com.marvel.ui.model.CharacterUIModel
import com.marvel.util.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MarvelCharacterListViewModel @Inject constructor(
    private val viewModelHelper: MarvelCharacterListViewModelHelper,
    private val getMarvelCharactersUseCase: GetMarvelCharactersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.IdleCharactersFirstPage)
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    var charactersUIModel: List<CharacterUIModel> = mutableListOf()
        private set

    fun getMarvelCharactersFirstPage() {
        getMarvelCharactersUseCase(offset = charactersUIModel.size)
            .flowOn(Dispatchers.IO)
            .onStart { _state.emit(State.LoadingCharactersFirstPage) }
            .onCompletion { _state.emit(State.IdleCharactersFirstPage) }
            .onEach { response ->
                if (response.isSuccess()) {
                    val charactersResponseDataModel = response.data.toCharactersResponseDataUIModel()
                    charactersUIModel = charactersResponseDataModel.characters
                    if (charactersUIModel.size < charactersResponseDataModel.total) {
                        _event.emit(Event.GetCharactersContinuePaging)
                    } else {
                        _event.emit(Event.GetCharactersFinishPaging)
                    }
                }
            }
            .catch { _state.emit(State.IdleCharactersFirstPage) }
            .launchIn(viewModelScope)
    }

    fun getMarvelCharactersNextPage() {
        getMarvelCharactersUseCase(offset = charactersUIModel.size)
            .flowOn(Dispatchers.IO)
            .onStart { _state.emit(State.LoadingNextCharactersPage) }
            .onCompletion { _state.emit(State.IdleNextCharactersPage) }
            .onEach { response ->
                if (response.isSuccess()) {
                    val charactersResponseDataModel = response.data.toCharactersResponseDataUIModel()
                    charactersUIModel = viewModelHelper.combineCharacterUIModels(
                        oldList = charactersUIModel,
                        newList = charactersResponseDataModel.characters
                    )
                    if (charactersUIModel.size < charactersResponseDataModel.total) {
                        _event.emit(Event.GetCharactersContinuePaging)
                    } else {
                        _event.emit(Event.GetCharactersFinishPaging)
                    }
                }
            }
            .catch { _state.emit(State.IdleCharactersFirstPage) }
            .launchIn(viewModelScope)
    }

    sealed class State {
        object IdleCharactersFirstPage : State()
        object LoadingCharactersFirstPage : State()
        object IdleNextCharactersPage : State()
        object LoadingNextCharactersPage : State()
    }

    sealed class Event {
        object GetCharactersFinishPaging : Event()
        object GetCharactersContinuePaging : Event()

        sealed class Error : Event() {
            data class ApiError(val serviceErrorDomain: ServiceErrorDomain) : Error()
        }
    }
}