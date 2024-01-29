package com.lightfeather.task.presentation.pages.postdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.lightfeather.task.R
import com.lightfeather.task.databinding.FragmentPostDetailsBinding
import com.lightfeather.task.ext.handleUiState
import com.lightfeather.task.presentation.pages.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostDetailsFragment : Fragment() {
    private val viewModel: PostsViewModel by viewModels()
    private lateinit var binding: FragmentPostDetailsBinding
    private val args: PostDetailsFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadPostById(args.postId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.postDetailsFlow.collect {
                handleUiState(it) {
                    with(binding) {
                        postTitle.text = it.title
                        postBody.text = it.body
                    }
                }
            }
        }
    }
}