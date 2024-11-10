package me.bossm0n5t3r.jmolecules.domains

import org.jmolecules.ddd.annotation.Repository

@Repository
class Articles {
    private val articles: MutableMap<Slug, Article> = mutableMapOf()

    fun save(article: Article): Slug {
        articles[article.slug] = article
        return article.slug
    }

    fun findBySlug(slug: Slug): Article? = articles[slug]

    fun findAll(): List<Article> = articles.values.toList()

    fun filterByStatus(status: Status): List<Article> = articles.values.filter { it.status == status }

    fun remove(slug: Slug) {
        articles.remove(slug)
    }
}
