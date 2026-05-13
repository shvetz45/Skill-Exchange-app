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
import com.example.skillexchangeapp.data.local.entity.NeedPost
import com.example.skillexchangeapp.databinding.FragmentPostNeedBinding
import com.example.skillexchangeapp.ui.viewmodel.MainViewModel
import com.example.skillexchangeapp.ui.viewmodel.ViewModelFactory
import com.example.skillexchangeapp.utils.SessionManager

class PostNeedFragment : Fragment() {

    private var _binding: FragmentPostNeedBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels {
        ViewModelFactory((requireActivity().application as SkillExchangeApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostNeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnSubmit.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()
            val skill = binding.etSkillRequired.text.toString()
            val hours = binding.etHours.text.toString().toIntOrNull() ?: 0
            val urgency = binding.etUrgency.text.toString()

            if (title.isNotEmpty() && skill.isNotEmpty()) {
                val userId = SessionManager(requireContext()).getUserId()
                val post = NeedPost(
                    userId = userId,
                    title = title,
                    description = description,
                    skillRequired = skill,
                    estimatedHours = hours,
                    urgencyLevel = urgency,
                    location = "Default" // Or extract from a field if available
                )
                viewModel.postNeed(post)
                Toast.makeText(context, "Need Posted Successfully", Toast.LENGTH_SHORT).show()
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
