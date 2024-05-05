package com.myaxa.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.myaxa.main_screen.di.DaggerMainFragmentComponent
import com.myaxa.main_screen.di.DaggerMainFragmentViewComponent
import com.myaxa.main_screen.di.MainFragmentComponent
import com.myaxa.main_screen.di.MainFragmentViewComponent
import com.myaxa.main_screen.di.MainScreenDependenciesProvider
import com.myaxa.mainscreen.databinding.FragmentMainBinding
import com.myaxa.common.unsafeLazy
import com.myaxa.search_api.SearchScreenApiProvider

class MainFragment : Fragment() {

    private val viewModelFactory: ViewModelProvider.Factory
        get() = (requireActivity().application as MainScreenDependenciesProvider)
            .provideMainScreenDependencies()
            .viewModelFactory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    private val fragmentComponent: MainFragmentComponent by unsafeLazy {
        DaggerMainFragmentComponent.factory().create(this, viewModel)
    }

    private var viewComponent: MainFragmentViewComponent? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val binding = FragmentMainBinding.inflate(inflater, container, false)

        viewComponent = DaggerMainFragmentViewComponent.factory().create(
            fragment = this,
            fragmentComponent = fragmentComponent,
            binding = binding,
            lifecycleOwner = viewLifecycleOwner,
            searchScreenApi = (requireActivity().application as SearchScreenApiProvider).provideSearchScreenApi()
        ).apply {
            viewController.setUpViews()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewComponent = null
    }
}