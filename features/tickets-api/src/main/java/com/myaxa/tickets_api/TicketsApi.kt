package com.myaxa.tickets_api

interface TicketsApiProvider {
    fun provideTicketsApi(): TicketsApi
}

interface TicketsApi {
    companion object {
        const val TICKETS_SCREEN_PARAMS = "tickets_screen_params"
    }
    fun provideTicketsFragmentNavigationId(): Int
}
