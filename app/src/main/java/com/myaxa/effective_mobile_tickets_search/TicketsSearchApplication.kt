package com.myaxa.effective_mobile_tickets_search

import android.app.Application
import com.myaxa.effective_mobile_tickets_search.di.ApplicationComponent
import com.myaxa.effective_mobile_tickets_search.di.DaggerApplicationComponent

class TicketsSearchApplication : Application(){
    private val component : ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create()
    }
}