package com.myaxa.search_impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myaxa.domain.models.DestinationsRepository
import com.myaxa.search_impl.models.DestinationUI
import com.myaxa.search_impl.models.toUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: DestinationsRepository,
) : ViewModel() {

    internal val destinationsFlow: StateFlow<List<DestinationUI>> = flow {
        emit(repository.getSearchDestinations().map { it.toUIModel() })
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _departureText: MutableStateFlow<String?> = MutableStateFlow(null)
    internal val departureText = _departureText.filterNotNull()

    internal fun updateDeparture(text: String?) {
        _departureText.update { text }
    }
}