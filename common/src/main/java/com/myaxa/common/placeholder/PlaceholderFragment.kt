package com.myaxa.common.placeholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myaxa.common.databinding.FragmentPlaceholderBinding

const val PLACEHOLDER_TEXT_KEY = "text"

internal class PlaceholderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val binding = FragmentPlaceholderBinding.inflate(inflater, container, false)

        val text = arguments?.getString(PLACEHOLDER_TEXT_KEY)
        binding.textPlaceholder.text = text ?: "Заглушка"

        return binding.root
    }
}