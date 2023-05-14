package ru.netology.nmedia.adapter

import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.PostcardLayoutBinding
import ru.netology.nmedia.utils.Utils

class PostViewHolder(
    private val binding: PostcardLayoutBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener,
    private val onRemoveListener: OnRemoveListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            date.text = post.published
            content.text = post.content
            likeButton.isChecked = post.likedByMe
            likeButton.setOnClickListener {
                onLikeListener(post)
            }
            shareButton.setOnClickListener {
                onShareListener(post)
            }
            likeText.text = Utils.getBeautifulCount(post.likes)
            shareText.text = Utils.getBeautifulCount(post.shares)
            viewsText.text = Utils.getBeautifulCount(post.views)

            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.post_options)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onRemoveListener(post)
                                true
                            }
                            else -> false

                        }
                    }
                }.show()
            }
        }
    }
}