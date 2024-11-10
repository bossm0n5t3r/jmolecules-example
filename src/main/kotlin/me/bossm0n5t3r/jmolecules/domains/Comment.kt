package me.bossm0n5t3r.jmolecules.domains

import jakarta.persistence.Table
import org.jmolecules.ddd.types.Entity
import org.jmolecules.ddd.types.Identifier
import java.time.LocalDateTime
import java.util.UUID

@Table(name = "comments")
class Comment(
    val username: Username,
    var message: String,
) : Entity<Article, Comment.CommentIdentifier> {
    data class CommentIdentifier(
        val id: String,
    ) : Identifier

    override val id: CommentIdentifier
        get() = CommentIdentifier(UUID.randomUUID().toString())

    var lastModified: LocalDateTime = LocalDateTime.now()
        private set

    init {
        require(message.isNotBlank()) { "Comment must not be blank" }
    }

    fun edit(newMessage: String) {
        message = newMessage
        lastModified = LocalDateTime.now()
    }
}
