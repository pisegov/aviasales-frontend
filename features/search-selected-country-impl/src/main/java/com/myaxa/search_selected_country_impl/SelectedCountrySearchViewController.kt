package com.myaxa.search_selected_country_impl

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.myaxa.common.SpaceItemDecoration
import com.myaxa.common.StringFormatter
import com.myaxa.common.StringPainter
import com.myaxa.common.collectOnLifecycle
import com.myaxa.common.setThrottleClickListener
import com.myaxa.search_selected_country_impl.databinding.FragmentSearchSelectedCountryBinding
import com.myaxa.search_selected_country_impl.models.ListItem
import java.time.LocalDate
import javax.inject.Inject
import com.myaxa.common.R as CommonR

internal class SelectedCountrySearchViewController @Inject constructor(
    private val fragment: Fragment,
    private val binding: FragmentSearchSelectedCountryBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: SelectedCountrySearchViewModel,
    private val ticketsOffersListAdapter: ListDelegationAdapter<List<ListItem>>,
    private val spaceItemDecoration: SpaceItemDecoration,
    private val stringPainter: StringPainter,
) {

    fun setUpViews(arrivalString: String, departureString: String) {

        setUpSearchCard(arrivalString, departureString)

        setUpButtonsCarousel()

        setUpDirectFlightsList()
    }

    private fun setUpSearchCard(
        arrivalString: String,
        departureString: String,
    ) = with(binding.searchSelectedCountrySearchCard) {

        arrival.setText(arrivalString)
        departure.setText(departureString)

        swapDestinations.setThrottleClickListener { swapArrivalAndDepartureText() }

        departureClear.setThrottleClickListener { departure.text?.clear() }

        backButton.setThrottleClickListener { fragment.findNavController().popBackStack() }
    }

    private fun setUpButtonsCarousel() = with(binding) {

        backTicketDatePicker.setThrottleClickListener {
            showDatePickerDialog(viewModel.pickedDate.value) { _, _, _, _ -> }
        }

        datePickerButton.setThrottleClickListener {
            showDatePickerDialog(viewModel.pickedDate.value) { _, year, month, dayOfMonth ->
                viewModel.updatePickedDate(LocalDate.of(year, month + 1, dayOfMonth))
            }
        }

        viewModel.pickedDate.collectOnLifecycle(lifecycleOwner) {
            updateDatePickerButton(it)
        }
    }

    private fun setUpDirectFlightsList()  {
        binding.directFlightsList.adapter = ticketsOffersListAdapter
        binding.directFlightsList.addItemDecoration(spaceItemDecoration)

        viewModel.ticketsOffers.collectOnLifecycle(lifecycleOwner) {
            ticketsOffersListAdapter.items = it
            ticketsOffersListAdapter.notifyItemRangeChanged(0, it.size)
        }
    }

    private fun swapArrivalAndDepartureText() = with(binding.searchSelectedCountrySearchCard) {
        val tmp = arrival.text
        arrival.text = departure.text
        departure.text = tmp
    }

    private fun showDatePickerDialog(date: LocalDate, updateDate: OnDateSetListener) {

        DatePickerDialog(
            fragment.requireContext(),
            updateDate,
            date.year,
            date.monthValue - 1,
            date.dayOfMonth
        ).show()
    }

    private fun updateDatePickerButton(localDate: LocalDate) {

        val string = StringFormatter.formatDate(localDate)

        binding.datePickerButton.text = stringPainter.paintSubstring(
            string,
            string.indexOf(','),
            string.length,
            CommonR.color.grey_6
        )
    }
}
