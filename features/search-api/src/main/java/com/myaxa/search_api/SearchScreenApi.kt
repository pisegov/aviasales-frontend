package com.myaxa.search_api

interface SearchScreenApiProvider {
    fun provideSearchScreenApi(): SearchScreenApi
}

interface SearchScreenApi {

    companion object {
        const val DEPARTURE_STRING = "departure_string"
    }

    fun provideSearchFragment(): Int
}
