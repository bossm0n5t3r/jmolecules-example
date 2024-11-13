package me.bossm0n5t3r.jmolecules.domains

import org.jmolecules.ddd.types.Identifier
import org.jmolecules.ddd.types.ValueObject

@JvmInline
value class Username(
    val value: String,
) : ValueObject,
    Identifier {
    init {
        require(value.isNotBlank()) { "Username value cannot be null or blank." }
    }
}

fun String.toUsername(): Username = Username(this)
