package me.bossm0n5t3r.jmolecules.domains

import org.jmolecules.ddd.types.Identifier
import org.jmolecules.ddd.types.ValueObject

data class Slug(
    val value: String,
) : ValueObject,
    Identifier {
    init {
        require(value.isNotBlank()) { "Slug must not be blank" }
        require(value.length >= 5) { "Article's slug should be at least 5 characters long!" }
        require(value.length <= 100) { "Article's slug should be at most 100 characters long!" }
    }
}

fun String.toSlug(): Slug = Slug(this.lowercase().replace(" ", "-"))
