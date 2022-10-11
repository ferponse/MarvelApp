package com.marvel.data.network

interface ServicesUrlProvider {

    fun getMarvelCharactersUrl(): String

    fun getMarvelCharacterByIdUrl(id: String): String

}