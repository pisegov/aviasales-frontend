package com.myaxa.effective_mobile_tickets_search.ui.placeholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myaxa.effective_mobile_tickets_search.databinding.FragmentPlaceholderBinding

class PlaceholderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val binding = FragmentPlaceholderBinding.inflate(inflater, container, false)

        binding.textPlaceholder.text = "Заглушка"

        return binding.root
    }
}