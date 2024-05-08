package com.myaxa.search_selected_country_impl.di

import androidx.fragment.app.Fragment
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.myaxa.common.SpaceItemDecoration
import com.myaxa.common.dpToPx
import com.myaxa.search_selected_country_impl.SelectedCountrySearchViewModel
import com.myaxa.search_selected_country_impl.TicketsOffersListAdapterFactory
import com.myaxa.search_selected_country_impl.models.ListItem
import com.myaxa.tickets_api.TicketsApi
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Component(modules = [SelectedCountrySearchFragmentModule::class])
@SelectedCountrySearchFragmentScope
internal interface SelectedCountrySearchFragmentComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment,
            @BindsInstance viewModel: SelectedCountrySearchViewModel,
            @BindsInstance ticketsApi: TicketsApi,
        ): SelectedCountrySearchFragmentComponent
    }

    val fragment: Fragment
    val viewModel: SelectedCountrySearchViewModel
    val adapter: ListDelegationAdapter<List<ListItem>>
    val spaceItemDecoration: SpaceItemDecoration
    val ticketsApi: TicketsApi
}

@Module
internal interface SelectedCountrySearchFragmentModule {
    companion object {
        @Provides
        fun provideSpaceItemDecorator() = SpaceItemDecoration(8.dpToPx())

        @Provides
        fun provideTicketsOffersListAdapter(
            factory: TicketsOffersListAdapterFactory,
        ): ListDelegationAdapter<List<ListItem>> = factory.create()
    }
}

@Scope
internal annotation class SelectedCountrySearchFragmentScope