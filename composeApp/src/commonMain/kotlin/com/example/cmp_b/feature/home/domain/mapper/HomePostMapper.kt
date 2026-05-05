package com.example.cmp_b.feature.home.domain.mapper

import com.example.cmp_b.core.base.Mapper
import com.example.cmp_b.core.utils.orDash
import com.example.cmp_b.feature.home.domain.model.HomePost
import com.example.cmp_b.shared.domain.model.Post

class HomePostMapper : Mapper<Post, HomePost> {
    override fun mapFrom(from: Post): HomePost {
        return HomePost(
            id = from.id,
            title = from.title.orDash(),
            body = from.body.orDash(),
            userId = from.userId
        )
    }

    fun mapFromList(posts: List<Post>?): List<HomePost> {
        return posts?.map { mapFrom(it) } ?: emptyList()
    }
}
