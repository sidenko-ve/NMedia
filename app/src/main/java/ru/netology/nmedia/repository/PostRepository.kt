package ru.netology.nmedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun save(post: Post)
    fun likeById(id: Long)
    fun shareById(id: Long)
    fun removeById(id: Long)
}

class PostRepositoryInMemoryImpl : PostRepository {
    private var posts = listOf(
        Post(
            1,
            "Нетология. Университет интернет-профессий",
            "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> http//:netolo.gy/fyb",
            "2 мая в 18:36",
            false,
            1999,
            2899,
            3100
        ),
        Post(
            2,
            "Нетология. Университет интернет-профессий",
            "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> http//:netolo.gy/fyb",
            "2 мая в 18:36",
            false,
            9,
            2899,
            31000000
        ),
    )

    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data


    override fun save(post: Post) {
        posts = listOf(
            post.copy(
                id = nextId++,
                author = "Me",
                likedByMe = false,
                published = "now"
            )
        ) + posts
        data.value = posts
        return
    }

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it
            else it.copy(
                likedByMe = !it.likedByMe,
                likes = if (!it.likedByMe) {
                    it.likes + 1
                } else it.likes - 1
            )
        }
        data.value = posts
    }

    override fun shareById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(shares = it.shares + 1)
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }
}