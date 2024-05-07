package com.myaxa.search_impl.di

import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.myaxa.common.setThrottleClickListener
import com.myaxa.search_impl.SearchViewModel
import com.myaxa.search_impl.databinding.ItemDestinationBinding
import com.myaxa.search_impl.models.DestinationUI
import com.myaxa.search_impl.models.ListItem
import com.myaxa.search_selected_country_api.SelectedCountrySearchScreenApi
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Component(modules = [SearchFragmentModule::class])
@SearchFragmentScope
internal interface SearchFragmentComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: BottomSheetDialogFragment,
            @BindsInstance viewModel: SearchViewModel,
            @BindsInstance selectedCountrySearchScreenApi: SelectedCountrySearchScreenApi,
        ): SearchFragmentComponent
    }

    val fragment: BottomSheetDialogFragment
    val viewModel: SearchViewModel
    val adapter: ListDelegationAdapter<List<ListItem>>
    val selectedCountrySearchScreenApi: SelectedCountrySearchScreenApi
}

@Module
internal interface SearchFragmentModule {
    companion object {
        @Provides
        fun provideDestinationAdapterDelegate(viewModel: SearchViewModel): AdapterDelegate<List<ListItem>> =
            adapterDelegateViewBinding<DestinationUI, ListItem, ItemDestinationBinding>(
                viewBinding = { inflater, parent ->
                    ItemDestinationBinding.inflate(inflater, parent, false)
                },
                block = {
                    bind {
                        binding.image.load(item.imageId)
                        binding.title.text = item.name
                        binding.container.setThrottleClickListener {
                            viewModel.updateDeparture(item.name)
                        }
                    }
                }
            )

        @Provides
        fun provideDestinationsListAdapter(
            destinationsDelegate: AdapterDelegate<List<ListItem>>,
        ): ListDelegationAdapter<List<ListItem>> =
            ListDelegationAdapter(destinationsDelegate)
    }
}

@Scope
internal annotation class SearchFragmentScope