package com.myaxa.effective_mobile_tickets_search.di

import com.myaxa.data.RepositoryImpl
import com.myaxa.domain.models.Repository
import com.myaxa.effective_mobile_tickets_search.BuildConfig
import com.myaxa.main_screen.di.MainScreenDependencies
import com.myaxa.network.RemoteDataSource
import com.myaxa.network.RetrofitModule
import com.myaxa.search_api.SearchScreenApi
import com.myaxa.search_impl.di.SearchScreenDependencies
import com.myaxa.search_impl.di.SearchScreenApiModule
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
    ]
)
@ApplicationScope
internal interface ApplicationComponent :
    MainScreenDependencies,
    SearchScreenDependencies {

    @Component.Factory
    interface Factory {
        fun create(

        ): ApplicationComponent
    }

    val searchScreenApi: SearchScreenApi
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
    fun bindRepository(impl: RepositoryImpl): Repository
}

@Scope
internal annotation class ApplicationScope