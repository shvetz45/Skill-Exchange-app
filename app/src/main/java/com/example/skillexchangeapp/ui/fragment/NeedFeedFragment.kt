package com.example.skillexchangeapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.skillexchangeapp.R
import com.example.skillexchangeapp.SkillExchangeApplication
import com.example.skillexchangeapp.databinding.FragmentNeedFeedBinding
import com.example.skillexchangeapp.ui.adapter.NeedAdapter
import com.example.skillexchangeapp.ui.viewmodel.MainViewModel
import com.example.skillexchangeapp.ui.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class NeedFeedFragment : Fragment() {

    private var _binding: FragmentNeedFeedBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels {
        ViewModelFactory((requireActivity().application as SkillExchangeApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNeedFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        val adapter = NeedAdapter { need ->
            val bundle = Bundle().apply { putLong("postId", need.id) }
            findNavController().navigate(R.id.action_needFeed_to_offer, bundle)
        }
        binding.rvNeeds.adapter = adapter

        lifecycleScope.launch {
            viewModel.allOpenNeeds.collect { needs ->
                adapter.submitList(needs)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
