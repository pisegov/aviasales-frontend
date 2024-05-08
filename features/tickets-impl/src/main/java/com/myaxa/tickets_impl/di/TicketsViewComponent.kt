package com.myaxa.tickets_impl.di

import androidx.lifecycle.LifecycleOwner
import com.myaxa.tickets_impl.TicketsViewController
import com.myaxa.tickets_impl.databinding.FragmentTicketsBinding
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Component(
    dependencies = [TicketsFragmentComponent::class],
    modules = [TicketsViewModule::class],
)
@TicketsViewScope
interface TicketsViewComponent {
    @Component.Factory
    interface Factory {
        fun create(
            fragmentComponent: TicketsFragmentComponent,
            @BindsInstance binding: FragmentTicketsBinding,
            @BindsInstance lifecycleOwner: LifecycleOwner,
        ): TicketsViewComponent
    }

    val viewController: TicketsViewController
}

@Module
interface TicketsViewModule

@Scope
annotation class TicketsViewScope