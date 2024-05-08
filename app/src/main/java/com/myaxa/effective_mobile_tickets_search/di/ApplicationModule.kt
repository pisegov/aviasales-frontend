package com.myaxa.effective_mobile_tickets_search.di

import com.myaxa.data.RepositoryImpl
import com.myaxa.domain.models.DestinationsRepository
import com.myaxa.domain.models.DirectFlightOffersRepository
import com.myaxa.domain.models.OffersRepository
import com.myaxa.domain.models.TicketsRepository
import com.myaxa.effective_mobile_tickets_search.BuildConfig
import com.myaxa.network.RemoteDataSource
import com.myaxa.network.RetrofitModule
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface ApplicationModule {

    companion object {

        @Provides
        @ApplicationScope
        fun provideRetrofitModule(): RetrofitModule {
            return RetrofitModule(baseUrl = BuildConfig.BASE_URL)
        }

        @Provides
        @ApplicationScope
        fun provideRemoteDataSource(retrofitModule: RetrofitModule): RemoteDataSource {
            return RemoteDataSource(retrofitModule)
        }
    }

    @Binds
    fun bindOffersRepository(impl: RepositoryImpl): OffersRepository

    @Binds
    fun bindDestinationsRepository(impl: RepositoryImpl): DestinationsRepository

    @Binds
    fun bindDirectFlightOffersRepository(impl: RepositoryImpl): DirectFlightOffersRepository

    @Binds
    fun bindTicketsRepository(impl: RepositoryImpl): TicketsRepository
}