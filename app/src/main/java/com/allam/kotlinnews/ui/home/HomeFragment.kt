package com.allam.kotlinnews.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.allam.kotlinnews.databinding.FragmentHomeBinding
import com.allam.kotlinnews.ui.State
import com.allam.kotlinnews.ui.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    private val homeViewModel: HomeViewModel by viewModels()

    lateinit var newsAdapter: NewsAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsAdapter = NewsAdapter()

        binding.newsRecView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }



        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.newsData.collectLatest { state ->

                if (state.isLoading()) binding.progressBar.visibility = View.VISIBLE
                else binding.progressBar.visibility = View.INVISIBLE

                when (state) {
                    is State.Error -> {
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                    }
                    is State.Loading -> {

                    }
                    is State.Success -> {
                        newsAdapter.submitList(state.data)
                    }
                }
            }
        }


    }


}