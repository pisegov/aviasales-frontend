package com.myaxa.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myaxa.domain.models.OffersRepository
import com.myaxa.main_screen.models.toUiModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: OffersRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.loadOffers()
        }
    }

    internal val offersFlow = repository.offersFlow.map { list -> list.map { it.toUiModel() } }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}