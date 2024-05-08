package com.myaxa.effective_mobile_tickets_search

import android.app.Application
import com.myaxa.effective_mobile_tickets_search.di.ApplicationComponent
import com.myaxa.effective_mobile_tickets_search.di.DaggerApplicationComponent
import com.myaxa.main_screen.di.MainScreenDependenciesProvider
import com.myaxa.search_api.SearchScreenApiProvider
import com.myaxa.search_impl.di.SearchScreenDependenciesProvider
import com.myaxa.search_selected_country_api.SelectedCountrySearchScreenApiProvider
import com.myaxa.search_selected_country_impl.di.SelectedCountrySearchScreenDependenciesProvider
import com.myaxa.tickets_api.TicketsApiProvider
import com.myaxa.tickets_impl.di.TicketsScreenDependenciesProvider

internal class TicketsSearchApplication : Application(),
                                          MainScreenDependenciesProvider,
                                          SearchScreenDependenciesProvider,
                                          SearchScreenApiProvider,
                                          SelectedCountrySearchScreenDependenciesProvider,
                                          SelectedCountrySearchScreenApiProvider,
                                          TicketsScreenDependenciesProvider,
                                          TicketsApiProvider {

    private val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create()
    }

    override fun provideMainScreenDependencies() = component

    override fun provideSearchFragmentDependencies() = component

    override fun provideSearchScreenApi() = component.searchScreenApi

    override fun provideSelectedCountrySearchDependencies() = component

    override fun provideSelectedCountrySearchScreenApi() = component.selectedCountrySearchScreenApi

    override fun provideTicketsScreenDependencies() = component

    override fun provideTicketsApi() = component.ticketsApi
}