package com.myaxa.main_screen.di

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import coil.load
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.myaxa.main_screen.MainViewModel
import com.myaxa.common.SpaceItemDecoration
import com.myaxa.main_screen.models.ListItem
import com.myaxa.main_screen.models.OfferUI
import com.myaxa.mainscreen.R
import com.myaxa.mainscreen.databinding.ItemOfferBinding
import com.myaxa.common.dpToPx
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
        fun provideRecyclerViewAdapter(): ListDelegationAdapter<List<ListItem>> {
            return ListDelegationAdapter(
                adapterDelegateViewBinding<OfferUI, ListItem, ItemOfferBinding>(
                    viewBinding = { inflater, root ->
                        ItemOfferBinding.inflate(inflater, root, false)
                    }
                ) {

                    bind {
                        binding.image.load(item.imageResource)
                        binding.title.text = item.title
                        binding.destination.text = item.town
                        binding.price.text = context.getString(R.string.item_list_price, item.price)
                    }
                }
            )
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