package com.myaxa.effective_mobile_tickets_search

import android.app.Application
import com.myaxa.effective_mobile_tickets_search.di.ApplicationComponent
import com.myaxa.effective_mobile_tickets_search.di.DaggerApplicationComponent
import com.myaxa.main_screen.di.MainScreenDependencies
import com.myaxa.main_screen.di.MainScreenDependenciesProvider
import com.myaxa.search_api.SearchScreenApi
import com.myaxa.search_api.SearchScreenApiProvider
import com.myaxa.search_impl.di.SearchScreenDependencies
import com.myaxa.search_impl.di.SearchScreenDependenciesProvider

internal class TicketsSearchApplication : Application(),
                                          MainScreenDependenciesProvider,
                                          SearchScreenDependenciesProvider,
                                          SearchScreenApiProvider {
    private val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create()
    }

    override fun provideMainScreenDependencies(): MainScreenDependencies {
        return component
    }

    override fun provideSearchFragmentDependencies(): SearchScreenDependencies {
        return component
    }

    override fun provideSearchScreenApi(): SearchScreenApi {
        return component.searchScreenApi
    }
}