package com.myaxa.search_selected_country_api

interface SelectedCountrySearchScreenApiProvider {
    fun provideSelectedCountrySearchScreenApi(): SelectedCountrySearchScreenApi
}

interface SelectedCountrySearchScreenApi {

    companion object {
        const val ARRIVAL_STRING = "arrival_string"
        const val DEPARTURE_STRING = "departure_string"
    }

    fun provideSelectedCountrySearchFragment(): Int
}
