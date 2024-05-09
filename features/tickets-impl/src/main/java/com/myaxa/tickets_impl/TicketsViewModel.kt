package com.myaxa.tickets_impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myaxa.domain.models.TicketsRepository
import com.myaxa.tickets_impl.models.toUiModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class TicketsViewModel @Inject constructor(
    private val repository: TicketsRepository,
) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.loadTickets()
        }
    }

    internal val ticketsFlow = repository.ticketsFlow
        .map { list -> list.map { it.toUiModel() } }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}