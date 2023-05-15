package ru.netology.nmedia.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.PostcardLayoutBinding
import ru.netology.nmedia.utils.Utils

class PostViewHolder(
    private val binding: PostcardLayoutBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            date.text = post.published
            content.text = post.content
            likeButton.setImageResource(
                if (post.likedByMe) R.drawable.baseline_favorite_24
                else R.drawable.baseline_favorite_border_24
            )
            likeButton.setOnClickListener {
                onLikeListener(post)
            }
            shareButton.setOnClickListener {
                onShareListener(post)
            }
            likeText.text = Utils.getBeautifulCount(post.likes)
            shareText.text = Utils.getBeautifulCount(post.shares)
            viewsText.text = Utils.getBeautifulCount(post.views)
        }
    }
}