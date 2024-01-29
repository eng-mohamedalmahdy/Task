package com.lightfeather.task.presentation.pages.allposts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lightfeather.task.R
import com.lightfeather.task.databinding.ListItemPostBinding
import com.lightfeather.task.presentation.model.UiPost

class PostsAdapter(private val onItemClick: (UiPost) -> Unit) : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    private var posts: List<UiPost> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemPostBinding.inflate(inflater, parent, false)
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) = holder.bind(posts[position])

    fun updateList(newItems: List<UiPost>) {
        val diffResult = DiffUtil.calculateDiff(PostsDiffCallback(posts, newItems))
        posts = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    // ViewHolder class
    inner class PostViewHolder(private val binding: ListItemPostBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: UiPost) {
            with(binding) {
                textTitle.text = post.title
                textBody.text = post.body
                root.setOnClickListener {
                    onItemClick(post)
                }
            }
        }
    }

    inner class PostsDiffCallback(
        private val oldList: List<UiPost>,
        private val newList: List<UiPost>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}
