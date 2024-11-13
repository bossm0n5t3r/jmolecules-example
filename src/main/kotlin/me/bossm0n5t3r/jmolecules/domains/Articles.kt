package me.bossm0n5t3r.jmolecules.domains

import org.jmolecules.ddd.annotation.Repository

@Repository
class Articles {
    private val articles: MutableMap<Article.ArticleIdentifier, Article> = mutableMapOf()

    fun save(article: Article): Article.ArticleIdentifier {
        articles[article.id] = article
        return article.id
    }

    fun findBySlug(id: Article.ArticleIdentifier): Article? = articles[id]

    fun findAll(): List<Article> = articles.values.toList()

    fun filterByStatus(status: Status): List<Article> = articles.values.filter { it.status == status }

    fun remove(id: Article.ArticleIdentifier) {
        articles.remove(id)
    }
}
