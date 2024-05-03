package com.myaxa.effective_mobile_tickets_search.di

import com.myaxa.data.RepositoryImpl
import com.myaxa.domain.models.Repository
import com.myaxa.effective_mobile_tickets_search.BuildConfig
import com.myaxa.network.RemoteDataSource
import com.myaxa.network.RetrofitModule
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Component(modules = [ApplicationModule::class])
@ApplicationScope
internal interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(

        ): ApplicationComponent
    }
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