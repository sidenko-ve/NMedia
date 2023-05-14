package ru.netology.nmedia.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.viewModel.PostViewModel
import ru.netology.nmedia.R
import ru.netology.nmedia.utils.Utils.getBeautifulCount
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->

            with(binding) {
                author.text = post.author
                date.text = post.published
                content.text = post.content
                likeButton?.setImageResource(
                    if (post.likedByMe) R.drawable.baseline_favorite_24
                    else R.drawable.baseline_favorite_border_24
                )
                likeText.text = getBeautifulCount(post.likes)
                shareText.text = getBeautifulCount(post.shares)
                viewsText.text = getBeautifulCount(post.views)
            }


        }
        binding.likeButton.setOnClickListener {
            viewModel.like()
        }

        binding.shareButton.setOnClickListener {
            viewModel.share()
        }
    }
}
