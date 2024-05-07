package com.myaxa.search_selected_country_impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myaxa.domain.models.DestinationsRepository
import com.myaxa.domain.models.DirectFlightOffersRepository
import com.myaxa.search_selected_country_impl.models.toUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

class SelectedCountrySearchViewModel @Inject constructor(
    private val repository: DirectFlightOffersRepository,
) : ViewModel() {

    private val _pickedDate = MutableStateFlow<LocalDate>(LocalDate.now())

    internal val pickedDate = _pickedDate.asStateFlow()

    internal val ticketsOffers= flow {
        emit(repository.getTicketsOffers().map { it.toUiModel() }.take(3))
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    internal fun updatePickedDate(date: LocalDate) = _pickedDate.update { date }
}