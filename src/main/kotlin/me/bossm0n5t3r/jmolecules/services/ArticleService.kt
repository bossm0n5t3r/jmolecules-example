package me.bossm0n5t3r.jmolecules.services

import me.bossm0n5t3r.jmolecules.domains.Article
import me.bossm0n5t3r.jmolecules.domains.ArticleRepository
import me.bossm0n5t3r.jmolecules.domains.Comment
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

    fun findAllBySlug(slug: Slug): List<Map<String, Any>> = articleRepository.findAllBySlug(slug).map { it.toMap() }

    fun findAll(): List<Map<String, Any>> = articleRepository.findAll().map { it.toMap() }

    fun filterByStatus(status: Status): List<Map<String, Any>> = articleRepository.findAllByStatus(status).map { it.toMap() }

    fun delete(articleId: Article.ArticleIdentifier) {
        val article = articleRepository.findById(articleId.id) ?: error("article not found")
        articleRepository.delete(article)
    }

    fun getComments(articleId: Article.ArticleIdentifier): List<Map<String, Any>> {
        val article = articleRepository.findById(articleId.id) ?: error("article not found")
        return article.comments.map { it.toMap() }
    }

    fun createComment(
        articleId: Article.ArticleIdentifier,
        request: Map<String, String>,
    ): Map<String, Any> {
        val article = articleRepository.findById(articleId.id) ?: error("article not found")
        val author = request["author"]?.toUsername() ?: error("author is required")
        val message = request["message"] ?: error("message is required")
        val comment = article.comment(author, message)
        articleRepository.save(article)
        return comment.toMap()
    }

    fun editComment(
        articleId: Article.ArticleIdentifier,
        commentId: Comment.CommentIdentifier,
        request: Map<String, String>,
    ): Map<String, Any> {
        val article = articleRepository.findById(articleId.id) ?: error("article not found")
        val comment = article.comments.find { it.id == commentId } ?: error("comment not found")
        val message = request["message"] ?: error("message is required")
        comment.edit(message)
        articleRepository.save(article)
        return comment.toMap()
    }

    fun deleteComment(
        articleId: Article.ArticleIdentifier,
        commentId: Comment.CommentIdentifier,
    ) {
        val article = articleRepository.findById(articleId.id) ?: error("article not found")
        article.deleteComment(commentId)
        articleRepository.save(article)
    }
}
