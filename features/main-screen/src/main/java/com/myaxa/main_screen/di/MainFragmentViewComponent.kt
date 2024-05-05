package com.myaxa.main_screen.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.myaxa.main_screen.MainFragmentViewController
import com.myaxa.mainscreen.databinding.FragmentMainBinding
import com.myaxa.search_api.SearchScreenApi
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Component(
    dependencies = [MainFragmentComponent::class],
    modules = [MainFragmentViewModule::class]
)
@MainFragmentViewScope
internal interface MainFragmentViewComponent {

    @Component.Factory
    interface Factory {
        fun create(
            fragmentComponent: MainFragmentComponent,
            @BindsInstance fragment: Fragment,
            @BindsInstance binding: FragmentMainBinding,
            @BindsInstance lifecycleOwner: LifecycleOwner,
            @BindsInstance searchScreenApi: SearchScreenApi,
        ): MainFragmentViewComponent
    }

    val viewController: MainFragmentViewController
}

@Module
internal interface MainFragmentViewModule

@Scope
internal annotation class MainFragmentViewScope