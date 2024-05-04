package com.myaxa.main_screen.di

import androidx.lifecycle.ViewModelProvider

interface MainScreenDependenciesProvider {
    fun provideMainScreenDependencies(): MainScreenDependencies
}

interface MainScreenDependencies {
    val viewModelFactory: ViewModelProvider.Factory
}