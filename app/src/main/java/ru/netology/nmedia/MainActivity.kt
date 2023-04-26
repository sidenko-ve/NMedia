package ru.netology.nmedia

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.annotation.StringRes
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            1,
            "Нетология. Университет интернет-профессий",
            "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> http//:netolo.gy/fyb",
            "2 мая в 18:36",
            false,
            1999,
            2899,
            3100
        )

        with(binding) {
            author.text = post.author
            date.text = post.published
            content.text = post.content
            likeText.text = getBeautifulCount(post.likes)
            shareText.text = getBeautifulCount(post.shares)
            viewsText.text = getBeautifulCount(post.shares)

            if (post.likedByMe) {
                likeButton?.setImageResource(R.drawable.baseline_favorite_24)
                likeText.text = getBeautifulCount(post.likes + 1)
            }

            likeButton?.setOnClickListener {
                post.likedByMe = !post.likedByMe
                likeButton?.setImageResource(
                    if (post.likedByMe) R.drawable.baseline_favorite_24
                    else R.drawable.baseline_favorite_border_24
                )

                if (post.likedByMe) {
                    likeText.text = getBeautifulCount(post.likes + 1)
                } else likeText.text = getBeautifulCount(post.likes)
            }

            shareButton?.setOnClickListener {
                shareText.text = getBeautifulCount(post.shares + 1)
            }

        }


    }

    fun getBeautifulCount(count: Long): String {
        var bCount = count.toString()

        if (count in 1000..9999) {
            val firstSymb = count / 1000
            val secondSymb = ((count / 100) % 10).toInt()

            if (secondSymb.equals(0)) {
                bCount = firstSymb.toString() + "K"
            } else
                bCount = "${firstSymb}.${secondSymb}K"
        } else if (count in 10000..999999) {
            bCount = (count / 1000).toString() + "K"

        } else if (count in 1000000..999999999) {
            val firstSymb = count / 1000000
            val secondSymb = ((count / 100000) % 10).toInt()
            if (secondSymb.equals(0)) {
                bCount = firstSymb.toString() + "M"
            } else
                bCount = "${firstSymb}.${secondSymb}M"
        }
        return bCount
    }
}
