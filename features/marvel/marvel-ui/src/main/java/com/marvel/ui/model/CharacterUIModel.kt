package com.marvel.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class CharactersResponseData(
    val total: Int,
    val characters: List<CharacterUIModel>
)

@Parcelize
data class CharacterUIModel(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: CharacterThumbnailUIModel,
    val resourceURI: String,
    val comics: CharacterInfoUIModel,
    val series: CharacterInfoUIModel,
    val stories: CharacterInfoUIModel,
    val events: CharacterInfoUIModel
) : Parcelable

@Parcelize
data class CharacterThumbnailUIModel(
    val thumbnailURL: String
) : Parcelable

@Parcelize
data class CharacterInfoUIModel(
    val available: Int,
    val collectionURI: String,
    val items: List<CharacterInfoItemUIModel>,
    val returned: Int
) : Parcelable

@Parcelize
data class CharacterInfoItemUIModel(
    val resourceURI: String,
    val name: String
) : Parcelable
