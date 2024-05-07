package com.myaxa.main_screen.di

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.myaxa.common.SpaceItemDecoration
import com.myaxa.common.dpToPx
import com.myaxa.main_screen.MainViewModel
import com.myaxa.main_screen.OffersListAdapterFactory
import com.myaxa.main_screen.models.ListItem
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Component(modules = [MainFragmentModule::class])
@MainFragmentScope
internal interface MainFragmentComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment,
            @BindsInstance viewModel: MainViewModel,
        ): MainFragmentComponent
    }

    val viewModel: MainViewModel
    val adapter: ListDelegationAdapter<List<ListItem>>
    val itemDecoration: SpaceItemDecoration
    val sharedPreferences: SharedPreferences?
}

@Module
internal interface MainFragmentModule {

    companion object {
        @Provides
        fun provideRecyclerViewAdapter(factory: OffersListAdapterFactory): ListDelegationAdapter<List<ListItem>> {
            return factory.create()
        }

        @Provides
        fun provideRecyclerItemDecoration(): SpaceItemDecoration {
            return SpaceItemDecoration(16.dpToPx())
        }

        @Provides
        fun provideSharedPreferences(fragment: Fragment): SharedPreferences? {
            return fragment.requireActivity().getPreferences(Context.MODE_PRIVATE)
        }
    }
}

@Scope
internal annotation class MainFragmentScope