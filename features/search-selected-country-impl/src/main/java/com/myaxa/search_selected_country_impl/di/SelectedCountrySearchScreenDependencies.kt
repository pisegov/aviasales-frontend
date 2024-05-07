package com.myaxa.search_selected_country_impl.di

import androidx.lifecycle.ViewModelProvider

interface SelectedCountrySearchScreenDependenciesProvider {
    fun provideSelectedCountrySearchDependencies(): SelectedCountrySearchScreenDependencies
}

interface SelectedCountrySearchScreenDependencies {
    val viewModelFactory: ViewModelProvider.Factory
}