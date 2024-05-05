package com.myaxa.search_impl.di

import com.myaxa.search_impl.SearchViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Component(modules = [SearchFragmentModule::class])
@SearchFragmentScope
internal interface SearchFragmentComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: BottomSheetDialogFragment,
            @BindsInstance viewModel: SearchViewModel,
        ): SearchFragmentComponent
    }
}

@Module
internal interface SearchFragmentModule

@Scope
internal annotation class SearchFragmentScope