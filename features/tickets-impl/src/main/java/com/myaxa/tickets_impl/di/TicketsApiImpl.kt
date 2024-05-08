package com.myaxa.tickets_impl.di

import com.myaxa.tickets_api.TicketsApi
import com.myaxa.tickets_impl.R
import javax.inject.Inject

internal class TicketsApiImpl @Inject constructor() : TicketsApi {
    override fun provideTicketsFragmentNavigationId(): Int = R.id.tickets_navigation
}
