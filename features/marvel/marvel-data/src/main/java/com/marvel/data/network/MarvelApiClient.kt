package com.marvel.data.network

import com.marvel.data.model.CharactersResponseDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface MarvelApiClient {

    @GET
    fun getCharacters(
        @Url url: String,
        @Query("ts") ts: Int,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<CharactersResponseDTO>

    @GET
    fun getCharacterById(
        @Url url: String,
        @Query("ts") ts: Int,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
    ): Call<CharactersResponseDTO>

}