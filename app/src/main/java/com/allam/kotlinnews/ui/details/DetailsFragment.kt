package com.allam.kotlinnews.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.allam.kotlinnews.databinding.FragmentDetailsBinding
import com.allam.kotlinnews.model.ChildrenData
import com.allam.kotlinnews.model.getPrimarySelfText
import com.allam.kotlinnews.model.getPrimaryThumbnailUrl
import com.allam.kotlinnews.ui.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()

    private val detailsViewModel: DetailsViewModel by viewModels()

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            detailsViewModel.getMovieDetail(args.articleId).collectLatest { state ->
                when (state) {
                    is State.Error -> {
                        //Logging error
                    }
                    is State.Loading -> {
                    }
                    is State.Success -> {
                        setNewsDetails(state.data)
                    }
                }
            }

        }
    }

    private fun setNewsDetails(childrenData: ChildrenData) {
        binding.titleMovie.text = childrenData.title
        binding.articleBody.text = childrenData.getPrimarySelfText()
        val imageUrl = childrenData.getPrimaryThumbnailUrl()
        if (imageUrl.isNullOrEmpty()) {
            binding.imageNews.visibility = View.GONE
        }
        binding.imageNews.load(imageUrl)
    }


}