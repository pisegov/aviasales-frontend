package com.myaxa.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myaxa.domain.models.OffersRepository
import com.myaxa.main_screen.models.OfferUI
import com.myaxa.main_screen.models.toUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: OffersRepository) : ViewModel() {

    private val _offersFlow = MutableStateFlow<List<OfferUI>>(emptyList())

    init {
        viewModelScope.launch {
            _offersFlow.update { repository.getOffers().map { it.toUiModel() } }
        }
    }

    internal val offersFlow get() = _offersFlow.asStateFlow()
}