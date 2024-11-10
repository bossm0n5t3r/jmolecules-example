package me.bossm0n5t3r.jmolecules.domains

import jakarta.persistence.Table
import org.jmolecules.ddd.types.AggregateRoot

@Table(name = "articles")
class Article(
    val author: Username,
    var title: String,
    var content: String,
    var status: Status = Status.DRAFT,
) : AggregateRoot<Article, Slug> {
    override val id: Slug
        get() = title.toSlug()

    val comments: MutableList<Comment> = mutableListOf()
    val likedBy: MutableSet<Username> = mutableSetOf()

    init {
        require(title.isNotBlank()) { "title cannot be blank" }
        require(content.isNotBlank()) { "content cannot be blank" }
    }

    fun comment(
        user: Username,
        message: String,
    ) {
        comments.add(Comment(username = user, message = message))
    }

    fun publish() {
        if (status === Status.DRAFT || status === Status.HIDDEN) {
            status = Status.PUBLISHED
        }
        throw IllegalStateException("we cannot publish an article with status=$status")
    }

    fun hide() {
        if (status === Status.PUBLISHED) {
            status = Status.HIDDEN
        }
        throw IllegalStateException("we cannot hide an article with status=$status")
    }

    fun archive() {
        status = Status.ARCHIVED
    }

    fun like(user: Username) {
        likedBy.add(user)
    }

    fun dislike(user: Username) {
        likedBy.remove(user)
    }
}
