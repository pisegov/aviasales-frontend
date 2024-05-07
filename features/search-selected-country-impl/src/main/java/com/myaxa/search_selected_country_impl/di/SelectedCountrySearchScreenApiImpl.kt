package com.myaxa.search_selected_country_impl.di

import com.myaxa.search_selected_country_api.SelectedCountrySearchScreenApi
import com.myaxa.search_selected_country_impl.R
import javax.inject.Inject

class SelectedCountrySearchScreenApiImpl @Inject constructor() : SelectedCountrySearchScreenApi {
    override fun provideSelectedCountrySearchFragment(): Int {
        return R.id.selected_country_search_navigation
    }
}