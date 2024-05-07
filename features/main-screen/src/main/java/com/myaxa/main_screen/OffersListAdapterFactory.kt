package com.myaxa.main_screen

import coil.load
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.myaxa.main_screen.models.ListItem
import com.myaxa.main_screen.models.OfferUI
import com.myaxa.mainscreen.R
import com.myaxa.mainscreen.databinding.ItemOfferBinding
import javax.inject.Inject

internal class OffersListAdapterFactory @Inject constructor() {
    fun create() = ListDelegationAdapter(
        getOffersAdapterDelegate()
    )

    private fun getOffersAdapterDelegate() = adapterDelegateViewBinding<OfferUI, ListItem, ItemOfferBinding>(
        viewBinding =
        { inflater, root ->
            ItemOfferBinding.inflate(inflater, root, false)
        }
    )
    {

        bind {
            binding.image.load(item.imageResource)
            binding.title.text = item.title
            binding.destination.text = item.town
            binding.price.text = context.getString(R.string.item_list_price, item.price)
        }
    }
}