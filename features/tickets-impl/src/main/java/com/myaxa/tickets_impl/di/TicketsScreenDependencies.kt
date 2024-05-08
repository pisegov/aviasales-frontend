package com.myaxa.tickets_impl.di

import androidx.lifecycle.ViewModelProvider

interface TicketsScreenDependenciesProvider {
    fun provideTicketsScreenDependencies(): TicketsScreenDependencies
}

interface TicketsScreenDependencies {
    val viewModelFactory: ViewModelProvider.Factory
}