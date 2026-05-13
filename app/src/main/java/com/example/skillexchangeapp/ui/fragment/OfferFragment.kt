package com.example.skillexchangeapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.skillexchangeapp.SkillExchangeApplication
import com.example.skillexchangeapp.data.local.entity.Offer
import com.example.skillexchangeapp.databinding.FragmentOfferBinding
import com.example.skillexchangeapp.ui.viewmodel.MainViewModel
import com.example.skillexchangeapp.ui.viewmodel.ViewModelFactory
import com.example.skillexchangeapp.utils.SessionManager

class OfferFragment : Fragment() {

    private var _binding: FragmentOfferBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels {
        ViewModelFactory((requireActivity().application as SkillExchangeApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOfferBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnSubmit.setOnClickListener {
            val message = binding.etMessage.text.toString()
            val skillsOffered = binding.etSkillsOffered.text.toString()

            if (message.isNotEmpty() && skillsOffered.isNotEmpty()) {
                val userId = SessionManager(requireContext()).getUserId()
                val postId = arguments?.getLong("postId") ?: -1L
                val offer = Offer(
                    needPostId = postId,
                    offeredByUserId = userId,
                    offeredSkill = skillsOffered,
                    offeredHours = 0, // Simplified for now
                    message = message
                )
                viewModel.submitOffer(offer)
                Toast.makeText(context, "Offer Submitted", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Please fill required fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
