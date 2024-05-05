package com.myaxa.search_impl.di

import androidx.lifecycle.LifecycleOwner
import com.myaxa.search_impl.SearchViewController
import com.myaxa.search_impl.databinding.FragmentSearchBinding
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Component(
    dependencies = [SearchFragmentComponent::class],
    modules = [SearchViewModule::class],
)
@SearchViewScope
internal interface SearchViewComponent {

    @Component.Factory
    interface Factory {
        fun create(
            fragmentComponent: SearchFragmentComponent,
            @BindsInstance binding: FragmentSearchBinding,
            @BindsInstance lifecycleOwner: LifecycleOwner,
        ): SearchViewComponent
    }

    val viewController: SearchViewController
}

@Module
internal interface SearchViewModule

@Scope
internal annotation class SearchViewScope