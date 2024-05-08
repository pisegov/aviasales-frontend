package com.myaxa.tickets_impl.di

import android.content.Context
import androidx.fragment.app.Fragment
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.myaxa.common.SpaceItemDecoration
import com.myaxa.common.dpToPx
import com.myaxa.tickets_impl.TicketsListAdapterFactory
import com.myaxa.tickets_impl.TicketsViewModel
import com.myaxa.tickets_impl.models.ListItem
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Component(modules = [TicketsFragmentModule::class])
@TicketsFragmentScope
interface TicketsFragmentComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment,
            @BindsInstance viewModel: TicketsViewModel,
        ): TicketsFragmentComponent
    }

    val fragment: Fragment
    val viewModel: TicketsViewModel
    val adapter: ListDelegationAdapter<List<ListItem>>
    val spaceItemDecoration: SpaceItemDecoration
}

@Module
interface TicketsFragmentModule {
    companion object {
        @Provides
        fun provideTicketsListAdapter(
            factory: TicketsListAdapterFactory,
        ): ListDelegationAdapter<List<ListItem>> = factory.create()

        @Provides
        fun provideSpaceItemDecoration() = SpaceItemDecoration(16.dpToPx())
    }
}

@Scope
annotation class TicketsFragmentScope
