package com.myaxa.search_selected_country_impl.di

import androidx.lifecycle.LifecycleOwner
import com.myaxa.search_selected_country_impl.SelectedCountrySearchViewController
import com.myaxa.search_selected_country_impl.databinding.FragmentSearchSelectedCountryBinding
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Component(
    dependencies = [SelectedCountrySearchFragmentComponent::class],
    modules = [SelectedCountrySearchViewModule::class],
)
@SelectedCountrySearchViewScope
internal interface SelectedCountrySearchViewComponent {

    @Component.Factory
    interface Factory {
        fun create(
            fragmentComponent: SelectedCountrySearchFragmentComponent,
            @BindsInstance binding: FragmentSearchSelectedCountryBinding,
            @BindsInstance lifecycleOwner: LifecycleOwner,
        ): SelectedCountrySearchViewComponent
    }

    val viewController: SelectedCountrySearchViewController
}

@Module
internal interface SelectedCountrySearchViewModule

@Scope
internal annotation class SelectedCountrySearchViewScope