package me.bossm0n5t3r.jmolecules.domains

import org.jmolecules.ddd.annotation.Entity
import org.jmolecules.ddd.annotation.Identity
import java.time.LocalDateTime
import java.util.UUID

@Entity
class Comment(
    @Identity
    val id: String = UUID.randomUUID().toString(),
    val username: Username,
    var message: String,
) {
    var lastModified: LocalDateTime = LocalDateTime.now()
        private set

    init {
        require(message.isNotBlank()) { "Comment must not be blank" }
    }

    fun edit(newMessage: String) {
        message = newMessage
        lastModified = LocalDateTime.now()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        other as Comment

        return id == other.id
    }
}
