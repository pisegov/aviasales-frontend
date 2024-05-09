package com.myaxa.tickets_impl

import android.content.Context
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.myaxa.common.StringPainter
import com.myaxa.common.dpToPx
import com.myaxa.tickets_impl.databinding.ItemTicketBinding
import com.myaxa.tickets_impl.models.ListItem
import com.myaxa.tickets_impl.models.TicketUI
import javax.inject.Inject
import com.myaxa.common.R as CommonR

class TicketsListAdapterFactory @Inject constructor() {
    fun create() = ListDelegationAdapter(getTicketsAdapterDelegate())

    private fun getTicketsAdapterDelegate(): AdapterDelegate<List<ListItem>> {
        return adapterDelegateViewBinding<TicketUI, ListItem, ItemTicketBinding>(
            viewBinding = { inflater, container ->
                ItemTicketBinding.inflate(inflater, container, false)
            })
        {

            bind {
                with(binding) {

                    setUpTopBadge(this, item)

                    price.text = context.getString(CommonR.string.common_price, item.price)

                    arrivalTime.text = item.arrivalTime
                    departureTime.text = item.departureTime

                    departureAirport.text = item.departureAirport
                    arrivalAirport.text = item.arrivalAirport

                    flightDuration.text = getDurationString(context, item)
                }
            }
        }
    }

    private fun setUpTopBadge(binding: ItemTicketBinding, item: TicketUI) = with(binding) {
        item.badge?.let {
            topBage.isVisible = true
            topBage.text = it

            container.setPadding(0, 8.dpToPx(), 0, 0)
            constraint.setPadding(16.dpToPx(), 21.dpToPx(), 16.dpToPx(), 16.dpToPx())
        } ?: run {
            topBage.isVisible = false

            container.setPadding(0)
            constraint.setPadding(16.dpToPx(), 16.dpToPx(), 16.dpToPx(), 23.dpToPx())
        }
    }

    private fun getDurationString(context: Context, item: TicketUI): CharSequence {
        val durationString = context.getString(
            R.string.flight_duration,
            item.flightDuration
        )

        return if (item.hasTransfer) {
            durationString
        } else {
            val withoutTransitString = context.getString(R.string.without_transit)
            val result = "$durationString / $withoutTransitString"
            val separatorIndex = durationString.lastIndex + 2

            StringPainter.paintSubstring(result, start = separatorIndex, end = separatorIndex + 1, context.getColor(CommonR.color.grey_6))
        }
    }
}