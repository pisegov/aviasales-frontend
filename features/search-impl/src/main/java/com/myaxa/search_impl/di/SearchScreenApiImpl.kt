package com.myaxa.search_impl.di

import com.myaxa.search_api.SearchScreenApi
import com.myaxa.search_impl.R
import javax.inject.Inject

class SearchScreenApiImpl @Inject constructor() : SearchScreenApi {
    override fun provideSearchFragment(): Int {
        return R.id.search_navigation
    }
}