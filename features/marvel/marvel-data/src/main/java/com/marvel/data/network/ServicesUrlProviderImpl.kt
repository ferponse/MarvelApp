package com.marvel.data.network

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServicesUrlProviderImpl @Inject constructor(): ServicesUrlProvider {

    private fun buildUrl(urlBase: String = "https://gateway.marvel.com", path: String) = "${urlBase}$path"

    override fun getMarvelCharactersUrl(): String = buildUrl(path = "/v1/public/characters")

    override fun getMarvelCharacterByIdUrl(id: String): String = buildUrl(path = "/v1/public/characters/$id")
}