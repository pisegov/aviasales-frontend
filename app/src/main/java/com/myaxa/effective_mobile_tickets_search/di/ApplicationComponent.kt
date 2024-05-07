package com.myaxa.effective_mobile_tickets_search.di

import com.myaxa.data.RepositoryImpl
import com.myaxa.domain.models.DestinationsRepository
import com.myaxa.domain.models.DirectFlightOffersRepository
import com.myaxa.domain.models.MainRepository
import com.myaxa.effective_mobile_tickets_search.BuildConfig
import com.myaxa.main_screen.di.MainScreenDependencies
import com.myaxa.network.RemoteDataSource
import com.myaxa.network.RetrofitModule
import com.myaxa.search_api.SearchScreenApi
import com.myaxa.search_impl.di.SearchScreenApiModule
import com.myaxa.search_impl.di.SearchScreenDependencies
import com.myaxa.search_selected_country_api.SelectedCountrySearchScreenApi
import com.myaxa.search_selected_country_impl.di.SelectedCountrySearchScreenApiModule
import com.myaxa.search_selected_country_impl.di.SelectedCountrySearchScreenDependencies
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Component(
    modules = [
        ApplicationModule::class,
        ViewModelModule::class,
        SearchScreenApiModule::class,
        SelectedCountrySearchScreenApiModule::class,
    ]
)
@ApplicationScope
internal interface ApplicationComponent :
    MainScreenDependencies,
    SearchScreenDependencies,
    SelectedCountrySearchScreenDependencies {

    @Component.Factory
    interface Factory {
        fun create(

        ): ApplicationComponent
    }

    val searchScreenApi: SearchScreenApi
    val selectedCountrySearchScreenApi: SelectedCountrySearchScreenApi
}

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
    fun bindMainRepository(impl: RepositoryImpl): MainRepository

    @Binds
    fun bindDestinationsRepository(impl: RepositoryImpl): DestinationsRepository

    @Binds
    fun bindDirectFlightOffersRepository(impl: RepositoryImpl): DirectFlightOffersRepository
}

@Scope
internal annotation class ApplicationScope