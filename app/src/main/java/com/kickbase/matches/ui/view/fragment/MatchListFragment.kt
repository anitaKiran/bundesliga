package com.kickbase.matches.ui.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.kickbase.matches.databinding.FragmentCompetitionsListBinding
import com.kickbase.matches.ui.view.adapter.MatchAdapter
import com.kickbase.matches.ui.viewmodel.MatchesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchListFragment : Fragment() {
    private var _binding: FragmentCompetitionsListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MatchesViewModel by viewModels()
    private val matchAdapter = MatchAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompetitionsListBinding.inflate(inflater, container, false)

        setupUI()
        observeCompetitions()

        return binding.root
    }

    private fun setupUI(){
        binding.recyclerview.apply {
            this.adapter = matchAdapter
        }
    }

    private fun observeCompetitions(){
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.competitions.collect { viewEventResponses->
                if (viewEventResponses.isLoading) {
                    Log.e("Fragment ", "loading")

                   // binding.progress.visibility = View.VISIBLE
                }
                if (viewEventResponses.error.isNotBlank()) {
                    Log.e("Fragment ", "error")

                    //binding.progress.visibility = View.GONE
                    Toast.makeText(requireContext(), viewEventResponses.error, Toast.LENGTH_SHORT).show()
                }
                viewEventResponses.data?.let {
                    Log.e("Fragment ", "Result")
                    //binding.progress.visibility = View.GONE
                    // set recyclerview data
                    matchAdapter.submitList(it.matchList)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}