package com.myaxa.search_selected_country_impl

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.myaxa.search_selected_country_impl.databinding.ItemTicketsOfferBinding
import com.myaxa.search_selected_country_impl.models.ListItem
import com.myaxa.search_selected_country_impl.models.TicketsOfferUI
import javax.inject.Inject
import com.myaxa.common.R as CommonR

internal class TicketsOffersListAdapterFactory @Inject constructor() {

    fun create(): ListDelegationAdapter<List<ListItem>> =
        ListDelegationAdapter(getTicketsOffersAdapterDelegate())

    private fun getTicketsOffersAdapterDelegate(): AdapterDelegate<List<ListItem>> =
        adapterDelegateViewBinding<TicketsOfferUI, ListItem, ItemTicketsOfferBinding>(
            viewBinding = { inflater, parent ->
                ItemTicketsOfferBinding.inflate(inflater, parent, false)
            },
            block = {
                bind {

                    binding.title.text = item.title
                    binding.price.text =
                        context.getString(CommonR.string.common_price, item.price)
                    binding.flightsTime.text = item.timeRange

                    val color = ContextCompat.getColor(context, item.badgeColor)
                    ViewCompat.setBackgroundTintList(binding.bage, ColorStateList.valueOf(color))
                }
            }
        )
}