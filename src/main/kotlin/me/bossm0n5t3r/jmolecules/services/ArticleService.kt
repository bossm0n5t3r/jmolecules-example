package me.bossm0n5t3r.jmolecules.services

import me.bossm0n5t3r.jmolecules.domains.Article
import me.bossm0n5t3r.jmolecules.domains.ArticleRepository
import me.bossm0n5t3r.jmolecules.domains.Slug
import me.bossm0n5t3r.jmolecules.domains.Status
import me.bossm0n5t3r.jmolecules.domains.toUsername
import org.springframework.stereotype.Service

@Service
class ArticleService(
    private val articleRepository: ArticleRepository,
) {
    fun create(request: Map<String, String>): Map<String, Any> {
        val author = request["author"]?.toUsername() ?: error("author is required")
        val title = request["title"] ?: error("title is required")
        val content = request["content"] ?: error("content is required")
        val article = Article(author, title, content)
        return articleRepository.save(article).toMap()
    }

    fun findBySlug(slug: Slug): Map<String, Any> = articleRepository.findBySlug(slug)?.toMap() ?: emptyMap()

    fun findAll(): List<Map<String, Any>> = articleRepository.findAll().map { it.toMap() }

    fun filterByStatus(status: Status): List<Map<String, Any>> = articleRepository.findAllByStatus(status).map { it.toMap() }

    fun delete(slug: Slug) {
        val article = articleRepository.findBySlug(slug) ?: error("article not found")
        articleRepository.delete(article)
    }
}
