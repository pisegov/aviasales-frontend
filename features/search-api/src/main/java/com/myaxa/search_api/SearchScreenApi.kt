package com.myaxa.search_api

interface SearchScreenApiProvider {
    fun provideSearchScreenApi(): SearchScreenApi
}

interface SearchScreenApi {

    companion object {
        const val ARRIVAL_STRING = "arrival_string"
    }

    fun provideSearchFragment(): Int
}
