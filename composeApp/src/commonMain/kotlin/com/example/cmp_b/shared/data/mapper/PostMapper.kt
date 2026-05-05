package com.example.cmp_b.shared.data.mapper

import com.example.cmp_b.core.base.Mapper
import com.example.cmp_b.core.network.model.PostDto
import com.example.cmp_b.core.utils.orDash
import com.example.cmp_b.shared.domain.model.Post

class PostMapper : Mapper<PostDto, Post> {
    override fun mapFrom(from: PostDto): Post {
        return Post(
            id = from.id ?: 0,
            title = from.title.orDash(),
            body = from.body.orDash(),
            userId = from.userId ?: 0
        )
    }

    fun mapFromList(postDtos: List<PostDto>?): List<Post> {
        return postDtos?.map { mapFrom(it) } ?: emptyList()
    }

    fun mapToDto(post: Post): PostDto {
        return PostDto(
            id = post.id,
            title = post.title,
            body = post.body,
            userId = post.userId
        )
    }
}
