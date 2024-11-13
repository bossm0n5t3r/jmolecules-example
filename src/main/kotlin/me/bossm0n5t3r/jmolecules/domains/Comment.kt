package me.bossm0n5t3r.jmolecules.domains

import jakarta.persistence.Id
import jakarta.persistence.Table
import org.jmolecules.ddd.types.Entity
import org.jmolecules.ddd.types.Identifier
import java.time.LocalDateTime
import java.util.UUID

@jakarta.persistence.Entity
@Table(name = "comments")
class Comment(
    val articleIdentifier: Article.ArticleIdentifier,
    val username: Username,
    var message: String,
) : Entity<Article, Comment.CommentIdentifier> {
    @JvmInline
    value class CommentIdentifier(
        val id: String,
    ) : Identifier

    @Id
    override val id: CommentIdentifier = CommentIdentifier(UUID.randomUUID().toString())

    var lastModified: LocalDateTime = LocalDateTime.now()
        private set

    init {
        require(message.isNotBlank()) { "Comment must not be blank" }
    }

    fun edit(newMessage: String) {
        message = newMessage
        lastModified = LocalDateTime.now()
    }

    fun toMap(): Map<String, Any> =
        mapOf(
            "id" to id.id,
            "articleIdentifier" to articleIdentifier.id,
            "username" to username.value,
            "message" to message,
            "lastModified" to lastModified,
        )
}
