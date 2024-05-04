package com.myaxa.effective_mobile_tickets_search.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myaxa.main_screen.MainViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
internal class ViewModelFactory @Inject constructor(
    private val viewModelFactories: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T  {
        return viewModelFactories.getValue(modelClass as Class<ViewModel>).get() as T
    }

    val viewModelClasses get() = viewModelFactories.keys
}

@Module
internal interface ViewModelModule {

    @Binds
    @ApplicationScope
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun mainViewModel(viewModel: MainViewModel): ViewModel
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)
