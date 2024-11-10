package me.bossm0n5t3r.jmolecules

import org.jmolecules.ddd.annotation.ValueObject

@ValueObject
data class Slug(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { "Slug must not be blank" }
        require(value.length >= 5) { "Article's slug should be at least 5 characters long!" }
        require(value.length <= 100) { "Article's slug should be at most 100 characters long!" }
    }
}
