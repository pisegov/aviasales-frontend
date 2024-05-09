package com.myaxa.search_impl

import coil.load
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.myaxa.common.setThrottleClickListener
import com.myaxa.search_impl.databinding.ItemDestinationBinding
import com.myaxa.search_impl.models.DestinationUI
import com.myaxa.search_impl.models.ListItem
import javax.inject.Inject

internal class DestinationsListAdapterFactory @Inject constructor(private val viewModel: SearchViewModel) {

    fun create() = ListDelegationAdapter(getDestinationAdapterDelegate(viewModel))

    private fun getDestinationAdapterDelegate(viewModel: SearchViewModel): AdapterDelegate<List<ListItem>> =
        adapterDelegateViewBinding<DestinationUI, ListItem, ItemDestinationBinding>(
            viewBinding = { inflater, parent ->
                ItemDestinationBinding.inflate(inflater, parent, false)
            },
            block = {
                bind {
                    binding.image.load(item.imageId)
                    binding.title.text = item.name
                    binding.container.setThrottleClickListener {
                        viewModel.updateArrival(item.name)
                    }
                }
            }
        )
}