package com.myaxa.search_impl

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.myaxa.common.collectOnLifecycle
import com.myaxa.common.dpToPx
import com.myaxa.common.placeholder.PLACEHOLDER_TEXT_KEY
import com.myaxa.search_impl.databinding.FragmentSearchBinding
import com.myaxa.search_impl.models.ListItem
import com.myaxa.search_impl.models.HintButtonInfo
import javax.inject.Inject

internal class SearchViewController @Inject constructor(
    private val fragment: BottomSheetDialogFragment,
    private val binding: FragmentSearchBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: SearchViewModel,
    private val destinationsListAdapter: ListDelegationAdapter<List<ListItem>>,
) {

    private val hintButtonToInfoMap = with(binding) {

        val navigateToPlaceholder = { text: String ->
            val bundle = Bundle().apply { putString(PLACEHOLDER_TEXT_KEY, text) }
            val nav = fragment.findNavController()
            nav.navigate(R.id.bottom_sheet_to_placeholder_navigation, bundle)
        }

        val updateDeparture = { text: String ->
            viewModel.updateDeparture(text)
        }

        mapOf(
            routeButton to HintButtonInfo.RouteButtonInfo(navigateToPlaceholder),
            globeButton to HintButtonInfo.GlobeButtonInfo(updateDeparture),
            weekendsButton to HintButtonInfo.WeekendsButtonInfo(navigateToPlaceholder),
            hotTicketsButton to HintButtonInfo.HotTicketsButtonInfo(navigateToPlaceholder),
        )
    }

    fun setUpViews(arrivalString: String) {

        setUpDialog()

        setUpSearchCard(arrivalString)

        setUpHintButtons()

        setUpDestinationsList()
    }

    private fun setUpDialog() {
        val height = Resources.getSystem().displayMetrics.heightPixels - 48.dpToPx()
        (fragment.dialog as BottomSheetDialog).behavior.apply {
            peekHeight = height
        }
        binding.container.minHeight = height
    }

    private fun setUpSearchCard(arrivalString: String) = with(binding.searchCard) {

        setUpFocusOnDeparture()

        binding.searchCard.arrival.setText(arrivalString)

        arrival.setOnFocusChangeListener { _, hasFocus -> arrivalClear.isVisible = hasFocus }
        departure.setOnFocusChangeListener { _, hasFocus -> departureClear.isVisible = hasFocus }

        arrivalClear.setOnClickListener { arrival.text?.clear() }
        departureClear.setOnClickListener { departure.text?.clear() }

        viewModel.departureText.collectOnLifecycle(lifecycleOwner) { text ->
            departure.setText(text)
            departure.setSelection(text.length)

            viewModel.updateDeparture(null)

            val navController = fragment.findNavController()
            val bundle = Bundle().apply { putString(PLACEHOLDER_TEXT_KEY, text) }
            navController.navigate(R.id.bottom_sheet_to_placeholder_navigation, bundle)
        }
    }

    private fun setUpFocusOnDeparture() {
        binding.searchCard.departure.requestFocus()

        with(fragment) {
            dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.showSoftInput(binding.searchCard.departure, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    private fun setUpHintButtons() = with(binding) {
        val context = root.context

        hintButtonToInfoMap.forEach { (buttonBinding, info) ->
            val text = ContextCompat.getString(context, info.titleId)
            with(buttonBinding) {
                container.setCardBackgroundColor(
                    ContextCompat.getColor(context, info.colorRes)
                )
                image.load(info.iconId)
                buttonTitle.text = text

                container.setOnClickListener { info.clickListener(text) }
            }
        }
    }

    private fun setUpDestinationsList() {
        binding.destinationsList.adapter = destinationsListAdapter
        viewModel.destinationsFlow.collectOnLifecycle(lifecycleOwner) {
            destinationsListAdapter.items = it
            destinationsListAdapter.notifyItemRangeChanged(0, it.size)
        }
    }
}
