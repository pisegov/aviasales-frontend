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
import com.myaxa.common.dpToPx
import com.myaxa.common.placeholder.PLACEHOLDER_TEXT_KEY
import com.myaxa.search_impl.databinding.FragmentSearchBinding
import com.myaxa.search_impl.models.SearchButtonInfo
import javax.inject.Inject

internal class SearchViewController @Inject constructor(
    private val fragment: BottomSheetDialogFragment,
    private val binding: FragmentSearchBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: SearchViewModel,
) {

    private val searchButtonToInfoMap = with(binding) {

        val navigateToPlaceholder = { text: String ->
            val bundle = Bundle().apply { putString(PLACEHOLDER_TEXT_KEY, text) }
            val nav = fragment.findNavController()
            nav.navigate(R.id.bottom_sheet_to_placeholder_navigation, bundle)
        }

        val updateDeparture = { text: String ->
            searchCard.departure.setText(text)
            searchCard.departure.setSelection(text.length)
        }

        mapOf(
            routeButton to SearchButtonInfo.RouteButtonInfo(navigateToPlaceholder),
            globeButton to SearchButtonInfo.GlobeButtonInfo(updateDeparture),
            weekendsButton to SearchButtonInfo.WeekendsButtonInfo(navigateToPlaceholder),
            hotTicketsButton to SearchButtonInfo.HotTicketsButtonInfo(navigateToPlaceholder),
        )
    }

    fun setUpViews(arrivalString: String) {

        setUpDialog()

        setUpSearchCard()

        setUpButtons()

        binding.searchCard.arrival.setText(arrivalString)
    }

    private fun setUpDialog() {
        val height = Resources.getSystem().displayMetrics.heightPixels - 48.dpToPx()
        (fragment.dialog as BottomSheetDialog).behavior.apply {
            peekHeight = height
        }
        binding.container.minHeight = height
    }

    private fun setUpSearchCard() = with(binding.searchCard) {

        setUpFocusOnDeparture()

        arrival.setOnFocusChangeListener { _, hasFocus -> arrivalClear.isVisible = hasFocus }
        departure.setOnFocusChangeListener { _, hasFocus -> departureClear.isVisible = hasFocus }

        arrivalClear.setOnClickListener { arrival.text?.clear() }
        departureClear.setOnClickListener { departure.text?.clear() }
    }

    private fun setUpFocusOnDeparture() {
        binding.searchCard.departure.requestFocus()

        with(fragment) {
            dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.showSoftInput(binding.searchCard.departure, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    private fun setUpButtons() = with(binding) {
        val context = root.context

        searchButtonToInfoMap.forEach { (buttonBinding, info) ->
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
}
