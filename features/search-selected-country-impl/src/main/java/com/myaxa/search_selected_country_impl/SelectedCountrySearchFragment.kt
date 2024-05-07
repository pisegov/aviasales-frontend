package com.myaxa.search_selected_country_impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.myaxa.common.unsafeLazy
import com.myaxa.search_selected_country_api.SelectedCountrySearchScreenApi
import com.myaxa.search_selected_country_impl.databinding.FragmentSearchSelectedCountryBinding
import com.myaxa.search_selected_country_impl.di.DaggerSelectedCountrySearchFragmentComponent
import com.myaxa.search_selected_country_impl.di.DaggerSelectedCountrySearchViewComponent
import com.myaxa.search_selected_country_impl.di.SelectedCountrySearchFragmentComponent
import com.myaxa.search_selected_country_impl.di.SelectedCountrySearchScreenDependenciesProvider
import com.myaxa.search_selected_country_impl.di.SelectedCountrySearchViewComponent

internal class SelectedCountrySearchFragment : Fragment() {

    private val viewModelFactory
        get() = (requireActivity().application as SelectedCountrySearchScreenDependenciesProvider)
            .provideSelectedCountrySearchDependencies().viewModelFactory

    private val viewModel: SelectedCountrySearchViewModel by viewModels { viewModelFactory }

    private val fragmentComponent: SelectedCountrySearchFragmentComponent by unsafeLazy {
        DaggerSelectedCountrySearchFragmentComponent.factory().create(
            fragment = this,
            viewModel = viewModel,
        )
    }

    private var viewComponent: SelectedCountrySearchViewComponent? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentSearchSelectedCountryBinding
            .inflate(inflater, container, false)

        viewComponent = DaggerSelectedCountrySearchViewComponent.factory().create(
            fragmentComponent = fragmentComponent,
            binding = binding,
            lifecycleOwner = viewLifecycleOwner,
        ).apply {
            val arrivalString = arguments?.getString(SelectedCountrySearchScreenApi.ARRIVAL_STRING) ?: ""
            val departureString = arguments?.getString(SelectedCountrySearchScreenApi.DEPARTURE_STRING) ?: ""

            viewController.setUpViews(arrivalString, departureString)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewComponent = null
    }
}