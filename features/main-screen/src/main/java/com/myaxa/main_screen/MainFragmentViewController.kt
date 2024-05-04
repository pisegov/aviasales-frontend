package com.myaxa.main_screen

import android.content.SharedPreferences
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.myaxa.common.collectOnLifecycle
import com.myaxa.common.textChanges
import com.myaxa.main_screen.models.ListItem
import com.myaxa.mainscreen.databinding.FragmentMainBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

internal class MainFragmentViewController @Inject constructor(
    private val binding: FragmentMainBinding,
    private val viewModel: MainViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val adapter: ListDelegationAdapter<List<ListItem>>,
    private val itemDecoration: SpaceItemDecoration,
    private val sharedPreferences: SharedPreferences?,
) {
    companion object {
        private const val ARRIVAL_TEXT_KEY = "arrival_text"
    }

    fun setUpViews() {

        setUpRecycler()

        setUpSearchCard()

        viewModel.offersFlow.collectOnLifecycle(lifecycleOwner) {
            adapter.items = it
            adapter.notifyItemRangeChanged(0, it.size)
        }
    }

    private fun setUpRecycler() {
        binding.recycler.adapter = adapter
        binding.recycler.addItemDecoration(itemDecoration)
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun setUpSearchCard() {
        binding.searchCard.arrival.setText(getArrivalText())
        binding.searchCard.arrival.textChanges()
            .debounce(500)
            .onEach {

                cacheArrivalText(it.toString())

            }.launchIn(lifecycleOwner.lifecycleScope)
    }

    private fun getArrivalText(): String {
        return sharedPreferences?.getString(ARRIVAL_TEXT_KEY, "") ?: ""
    }

    private fun cacheArrivalText(text: String) {
        sharedPreferences?.edit()?.run {
            putString(ARRIVAL_TEXT_KEY, text)
            apply()
        }
    }
}

