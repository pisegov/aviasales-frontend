package com.myaxa.search_selected_country_impl.di

import com.myaxa.search_selected_country_api.SelectedCountrySearchScreenApi
import dagger.Binds
import dagger.Module

@Module
abstract class SelectedCountrySearchScreenApiModule {
    @Binds
    internal abstract fun bindSearchScreenApi(impl: SelectedCountrySearchScreenApiImpl): SelectedCountrySearchScreenApi
}