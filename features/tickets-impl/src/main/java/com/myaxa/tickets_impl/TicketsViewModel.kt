package com.myaxa.tickets_impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myaxa.domain.models.TicketsRepository
import com.myaxa.tickets_impl.models.toUiModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class TicketsViewModel @Inject constructor(
    private val repository: TicketsRepository,
) : ViewModel() {

    internal val ticketsFlow = flow {
        emit(repository.getTickets().map { it.toUiModel() })
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}