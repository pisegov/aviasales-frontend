package com.myaxa.search_impl.di

import com.myaxa.search_api.SearchScreenApi
import dagger.Binds
import dagger.Module

@Module
abstract class SearchScreenApiModule {
    @Binds
    internal abstract fun bindSearchScreenApi(impl: SearchScreenApiImpl): SearchScreenApi
}