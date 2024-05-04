package com.myaxa.effective_mobile_tickets_search

import android.app.Application
import com.myaxa.effective_mobile_tickets_search.di.ApplicationComponent
import com.myaxa.effective_mobile_tickets_search.di.DaggerApplicationComponent
import com.myaxa.main_screen.di.MainScreenDependencies
import com.myaxa.main_screen.di.MainScreenDependenciesProvider

internal class TicketsSearchApplication : Application(), MainScreenDependenciesProvider{
    private val component : ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create()
    }

    override fun provideMainScreenDependencies(): MainScreenDependencies {
        return component
    }
}