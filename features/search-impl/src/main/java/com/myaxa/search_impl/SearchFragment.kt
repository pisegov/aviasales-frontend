package com.myaxa.search_impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.myaxa.common.unsafeLazy
import com.myaxa.search_api.SearchScreenApi
import com.myaxa.search_impl.di.SearchScreenDependenciesProvider
import com.myaxa.search_impl.databinding.FragmentSearchBinding
import com.myaxa.search_impl.di.DaggerSearchFragmentComponent
import com.myaxa.search_impl.di.DaggerSearchViewComponent
import com.myaxa.search_impl.di.SearchFragmentComponent
import com.myaxa.search_impl.di.SearchViewComponent
import com.myaxa.search_selected_country_api.SelectedCountrySearchScreenApiProvider

internal class SearchFragment : BottomSheetDialogFragment() {

    private val viewModelFactory
        get() = (requireActivity().application as SearchScreenDependenciesProvider).provideSearchFragmentDependencies().viewModelFactory

    private val viewModel: SearchViewModel by viewModels { viewModelFactory }

    private val fragmentComponent: SearchFragmentComponent by unsafeLazy {
        DaggerSearchFragmentComponent.factory().create(
            fragment = this,
            viewModel = viewModel,
            selectedCountrySearchScreenApi = (requireActivity().application as SelectedCountrySearchScreenApiProvider)
                .provideSelectedCountrySearchScreenApi()
        )
    }

    private var viewComponent: SearchViewComponent? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentSearchBinding.inflate(inflater, container, false)

        viewComponent = DaggerSearchViewComponent.factory().create(
            fragmentComponent = fragmentComponent,
            binding = binding,
            lifecycleOwner = viewLifecycleOwner,
        ).apply {
            viewController.setUpViews(arguments?.getString(SearchScreenApi.DEPARTURE_STRING) ?: "")
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewComponent = null
    }
}