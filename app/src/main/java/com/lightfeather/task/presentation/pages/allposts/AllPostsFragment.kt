package com.lightfeather.task.presentation.pages.allposts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lightfeather.task.databinding.FragmentAllPostsBinding
import com.lightfeather.task.ext.handleUiState
import com.lightfeather.task.presentation.pages.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllPostsFragment : Fragment() {

    private val viewModel: PostsViewModel by viewModels()
    private lateinit var binding: FragmentAllPostsBinding
    private lateinit var postsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadPosts()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllPostsBinding.inflate(inflater, container, false)
        postsAdapter = PostsAdapter {
            findNavController().navigate(
                AllPostsFragmentDirections.actionAllPostsFragmentToPostDetailsFragment(it.id)
            )
        }
        binding.postsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = postsAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.postsList.collect {
                handleUiState(it) {
                    postsAdapter.updateList(it)
                }
            }
        }
    }
}