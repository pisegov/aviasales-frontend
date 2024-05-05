package com.myaxa.search_impl.di

import androidx.lifecycle.ViewModelProvider

interface SearchScreenDependenciesProvider {
    fun provideSearchFragmentDependencies(): SearchScreenDependencies
}

interface SearchScreenDependencies {
    val viewModelFactory: ViewModelProvider.Factory
}