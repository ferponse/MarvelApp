package com.marvel.data.model

import com.google.gson.annotations.SerializedName

data class CharactersResponseDTO(
    @SerializedName("data") val data: CharactersResponseDataDTO
)

data class CharactersResponseDataDTO(
    @SerializedName("total") val total: Int,
    @SerializedName("results") val results: List<CharactersResponseDataResultDTO>
)

data class CharactersResponseDataResultDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("thumbnail") val thumbnail: CharactersResponseDataResultThumbnailDTO,
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("comics") val comics: CharactersResponseDataResultInfoDTO,
    @SerializedName("series") val series: CharactersResponseDataResultInfoDTO,
    @SerializedName("stories") val stories: CharactersResponseDataResultInfoDTO,
    @SerializedName("events") val events: CharactersResponseDataResultInfoDTO
)

data class CharactersResponseDataResultThumbnailDTO(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String
)

data class CharactersResponseDataResultInfoDTO(
    @SerializedName("available") val available: Int,
    @SerializedName("collectionURI") val collectionURI: String,
    @SerializedName("items") val items: List<CharactersResponseDataResultInfoItemDTO>,
    @SerializedName("returned") val returned: Int
)

data class CharactersResponseDataResultInfoItemDTO(
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("name") val name: String
)
