package com.myaxa.tickets_impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.myaxa.common.unsafeLazy
import com.myaxa.tickets_api.TicketsApi
import com.myaxa.tickets_api.TicketsScreenParams
import com.myaxa.tickets_impl.databinding.FragmentTicketsBinding
import com.myaxa.tickets_impl.di.DaggerTicketsFragmentComponent
import com.myaxa.tickets_impl.di.DaggerTicketsViewComponent
import com.myaxa.tickets_impl.di.TicketsFragmentComponent
import com.myaxa.tickets_impl.di.TicketsScreenDependenciesProvider
import com.myaxa.tickets_impl.di.TicketsViewComponent

internal class TicketsFragment : Fragment() {

    private val viewModelFactory
        get() = (requireActivity().application as TicketsScreenDependenciesProvider)
            .provideTicketsScreenDependencies()
            .viewModelFactory

    private val viewModel: TicketsViewModel by viewModels { viewModelFactory }

    private val fragmentComponent: TicketsFragmentComponent by unsafeLazy {
        DaggerTicketsFragmentComponent.factory().create(
            fragment = this,
            viewModel = viewModel
        )
    }

    private var viewComponent: TicketsViewComponent? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentTicketsBinding.inflate(inflater, container, false)

        val params = arguments?.getParcelable<TicketsScreenParams>(TicketsApi.TICKETS_SCREEN_PARAMS)

        viewComponent = DaggerTicketsViewComponent.factory().create(
            fragmentComponent = fragmentComponent,
            binding = binding,
            lifecycleOwner = viewLifecycleOwner,
        ).apply {
            viewController.setUpViews(params)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewComponent = null
    }
}
