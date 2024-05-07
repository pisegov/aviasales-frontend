package com.myaxa.search_selected_country_impl.di

import android.content.Context
import android.content.res.ColorStateList
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.myaxa.common.SpaceItemDecoration
import com.myaxa.common.StringPainter
import com.myaxa.common.dpToPx
import com.myaxa.search_selected_country_impl.R
import com.myaxa.search_selected_country_impl.SelectedCountrySearchViewModel
import com.myaxa.search_selected_country_impl.databinding.ItemTicketsOfferBinding
import com.myaxa.search_selected_country_impl.models.ListItem
import com.myaxa.search_selected_country_impl.models.TicketsOfferUI
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
        ): SelectedCountrySearchFragmentComponent
    }

    val fragment: Fragment
    val viewModel: SelectedCountrySearchViewModel
    val adapter: ListDelegationAdapter<List<ListItem>>
    val spaceItemDecoration: SpaceItemDecoration
    val stringPainter: StringPainter
}

@Module
internal interface SelectedCountrySearchFragmentModule {
    companion object {
        @Provides
        fun provideApplicationContext(fragment: Fragment): Context {
            return fragment.requireActivity().applicationContext
        }

        @Provides
        fun provideSpaceItemDecorator() = SpaceItemDecoration(8.dpToPx())

        @Provides
        fun provideTicketsOffersAdapterDelegate(): AdapterDelegate<List<ListItem>> =
            adapterDelegateViewBinding<TicketsOfferUI, ListItem, ItemTicketsOfferBinding>(
                viewBinding = { inflater, parent ->
                    ItemTicketsOfferBinding.inflate(inflater, parent, false)
                },
                block = {
                    bind {
                        binding.title.text = item.title
                        binding.price.text =
                            context.getString(R.string.item_tickets_offer_price, item.price)
                        binding.flightsTime.text = item.timeRange

                        val color = ContextCompat.getColor(context, item.badgeColor)
                        ViewCompat.setBackgroundTintList(binding.bage, ColorStateList.valueOf(color))
                    }
                }
            )

        @Provides
        fun provideTicketsOffersListAdapter(
            ticketsOffersDelegate: AdapterDelegate<List<ListItem>>,
        ): ListDelegationAdapter<List<ListItem>> =
            ListDelegationAdapter(ticketsOffersDelegate)
    }
}

@Scope
internal annotation class SelectedCountrySearchFragmentScope