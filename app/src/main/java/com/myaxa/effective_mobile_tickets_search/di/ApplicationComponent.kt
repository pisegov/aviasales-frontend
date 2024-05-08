package com.myaxa.effective_mobile_tickets_search.di

import com.myaxa.main_screen.di.MainScreenDependencies
import com.myaxa.search_api.SearchScreenApi
import com.myaxa.search_impl.di.SearchScreenApiModule
import com.myaxa.search_impl.di.SearchScreenDependencies
import com.myaxa.search_selected_country_api.SelectedCountrySearchScreenApi
import com.myaxa.search_selected_country_impl.di.SelectedCountrySearchScreenApiModule
import com.myaxa.search_selected_country_impl.di.SelectedCountrySearchScreenDependencies
import com.myaxa.tickets_api.TicketsApi
import com.myaxa.tickets_impl.di.TicketsApiModule
import com.myaxa.tickets_impl.di.TicketsScreenDependencies
import dagger.Component
import javax.inject.Scope

@Component(
    modules = [
        ApplicationModule::class,
        ViewModelModule::class,
        SearchScreenApiModule::class,
        SelectedCountrySearchScreenApiModule::class,
        TicketsApiModule::class,
    ]
)
@ApplicationScope
internal interface ApplicationComponent :
    MainScreenDependencies,
    SearchScreenDependencies,
    SelectedCountrySearchScreenDependencies,
    TicketsScreenDependencies {

    @Component.Factory
    interface Factory {
        fun create(

        ): ApplicationComponent
    }

    val searchScreenApi: SearchScreenApi
    val selectedCountrySearchScreenApi: SelectedCountrySearchScreenApi
    val ticketsApi: TicketsApi
}

@Scope
internal annotation class ApplicationScope