package com.myaxa.tickets_impl.di

import com.myaxa.tickets_api.TicketsApi
import dagger.Binds
import dagger.Module

@Module
abstract class TicketsApiModule {
    @Binds
    internal abstract fun bindTicketsApi(impl: TicketsApiImpl): TicketsApi
}