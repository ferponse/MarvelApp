package com.marvel.ui.screen.characterdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marvel.ui.model.CharacterUIModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MarvelCharacterDetailViewModel @Inject constructor(
) : ViewModel() {

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    lateinit var characterUIModel: CharacterUIModel
        private set

    fun setCharacter(characterUIModel: CharacterUIModel) {
        this.characterUIModel = characterUIModel
        viewModelScope.launch {
            _event.emit(Event.CharacterSetUp)
        }
    }

    sealed class Event {
        object CharacterSetUp : Event()
    }

}