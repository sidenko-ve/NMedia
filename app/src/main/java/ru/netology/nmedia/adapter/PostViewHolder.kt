package ru.netology.nmedia.adapter

import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.PostcardLayoutBinding
import ru.netology.nmedia.utils.Utils

//ViewHolder (иногда также называемый View Holder) - это паттерн проектирования,
// который используется в андроид-разработке совместно с адаптерами, особенно в RecyclerView,
// для повышения производительности при работе с элементами пользовательского интерфейса.
class PostViewHolder(
    private val binding: PostcardLayoutBinding,
    private val listener: PostListener

) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            date.text = post.published
            content.text = post.content
            likeButton.isChecked = post.likedByMe
            likeButton.setOnClickListener {
                listener.onLike(post)
            }
            shareButton.setOnClickListener {
                listener.onShare(post)
            }
            likeText.text = Utils.getBeautifulCount(post.likes)
            shareText.text = Utils.getBeautifulCount(post.shares)
            viewsText.text = Utils.getBeautifulCount(post.views)

            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                listener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                listener.onEdit(post)
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