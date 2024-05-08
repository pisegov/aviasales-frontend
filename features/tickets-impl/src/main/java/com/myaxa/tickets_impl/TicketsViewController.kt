package com.myaxa.tickets_impl

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.myaxa.common.SpaceItemDecoration
import com.myaxa.common.StringFormatter
import com.myaxa.common.collectOnLifecycle
import com.myaxa.common.setThrottleClickListener
import com.myaxa.tickets_api.TicketsScreenParams
import com.myaxa.tickets_impl.databinding.FragmentTicketsBinding
import com.myaxa.tickets_impl.models.ListItem
import javax.inject.Inject

class TicketsViewController @Inject constructor(
    private val fragment: Fragment,
    private val binding: FragmentTicketsBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: TicketsViewModel,
    private val ticketsListAdapter: ListDelegationAdapter<List<ListItem>>,
    private val spaceItemDecoration: SpaceItemDecoration,
) {
    fun setUpViews(params: TicketsScreenParams?) {

        params?.let { setUpToolbar(it) }

        setUpTicketsList()
    }

    private fun setUpToolbar(params: TicketsScreenParams) = with(binding) {

        route.text = "${params.departure}-${params.arrival}".trim()
            .removePrefix("-").removeSuffix("-")

        info.text = "${StringFormatter.formatDate(params.departureDate)}, 1 пассажир"

        backButton.setThrottleClickListener {
            fragment.findNavController().popBackStack()
        }
    }

    private fun setUpTicketsList() {
        binding.list.adapter = ticketsListAdapter
        binding.list.addItemDecoration(spaceItemDecoration)

        viewModel.ticketsFlow.collectOnLifecycle(lifecycleOwner) {
            val oldSize = ticketsListAdapter.items?.size ?: 0
            ticketsListAdapter.items = it
            ticketsListAdapter.notifyItemRangeChanged(0, maxOf(it.size, oldSize))
        }
    }
}