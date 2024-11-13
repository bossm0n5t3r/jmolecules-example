package me.bossm0n5t3r.jmolecules.domains

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.jmolecules.ddd.types.AggregateRoot
import org.jmolecules.ddd.types.Identifier
import java.util.UUID

@Entity
@Table(name = "articles")
class Article(
    val author: Username,
    var title: String,
    var content: String,
    @Enumerated(EnumType.STRING)
    var status: Status = Status.DRAFT,
) : AggregateRoot<Article, Article.ArticleIdentifier> {
    @JvmInline
    value class ArticleIdentifier(
        val id: UUID,
    ) : Identifier

    @Id
    override val id: ArticleIdentifier = ArticleIdentifier(UUID.randomUUID())

    val slug = title.toSlug()

    @OneToMany(mappedBy = "articleIdentifier")
    val comments: MutableList<Comment> = mutableListOf()

    var likedBy = ""

    init {
        require(title.isNotBlank()) { "title cannot be blank" }
        require(content.isNotBlank()) { "content cannot be blank" }
    }

    fun comment(
        user: Username,
        message: String,
    ) {
        comments.add(Comment(articleIdentifier = this.id, username = user, message = message))
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
        likedBy = "$likedBy,${user.value}"
    }

    fun dislike(user: Username) {
        val users = likedBy.split(",")
        likedBy = users.filter { it != user.value }.joinToString(",")
    }

    fun toMap(): Map<String, Any> =
        mapOf(
            "id" to id.id,
            "author" to author.value,
            "title" to title,
            "content" to content,
            "status" to status,
            "slug" to slug,
            "comments" to comments.map { it.toMap() },
            "likedBy" to likedBy,
        )
}
