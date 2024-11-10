package me.bossm0n5t3r.jmolecules

import org.jmolecules.ddd.annotation.ValueObject

@ValueObject
data class Username(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { "Username value cannot be null or blank." }
    }
}
