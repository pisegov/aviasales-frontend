package com.myaxa.main_screen.di

import androidx.lifecycle.LifecycleOwner
import com.myaxa.main_screen.MainFragmentViewController
import com.myaxa.mainscreen.databinding.FragmentMainBinding
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
            @BindsInstance binding: FragmentMainBinding,
            @BindsInstance lifecycleOwner: LifecycleOwner,
        ): MainFragmentViewComponent
    }

    val viewController: MainFragmentViewController
}

@Module
internal interface MainFragmentViewModule

@Scope
internal annotation class MainFragmentViewScope