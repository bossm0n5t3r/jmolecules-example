package me.bossm0n5t3r.jmolecules.controllers

import me.bossm0n5t3r.jmolecules.domains.Article
import me.bossm0n5t3r.jmolecules.domains.Comment
import me.bossm0n5t3r.jmolecules.domains.Slug
import me.bossm0n5t3r.jmolecules.domains.Status
import me.bossm0n5t3r.jmolecules.services.ArticleService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/articles")
class ArticleController(
    private val articleService: ArticleService,
) {
    @PostMapping
    fun createArticle(
        @RequestBody request: Map<String, String>,
    ): Map<String, Any> = articleService.create(request)

    @GetMapping("/{slug}")
    fun findAllBySlug(
        @PathVariable slug: Slug,
    ): List<Map<String, Any>> = articleService.findAllBySlug(slug)

    @GetMapping
    fun findAll(): List<Map<String, Any>> = articleService.findAll()

    @GetMapping("/status/{status}")
    fun filterByStatus(
        @PathVariable status: Status,
    ): List<Map<String, Any>> = articleService.filterByStatus(status)

    @DeleteMapping("/{articleId}")
    fun deleteArticle(
        @PathVariable articleId: Article.ArticleIdentifier,
    ) {
        articleService.delete(articleId)
    }

    @GetMapping("/{articleId}/comments")
    fun getComments(
        @PathVariable articleId: Article.ArticleIdentifier,
    ): List<Map<String, Any>> = articleService.getComments(articleId)

    @PostMapping("/{articleId}/comments")
    fun createComment(
        @PathVariable articleId: Article.ArticleIdentifier,
        @RequestBody request: Map<String, String>,
    ): Map<String, Any> = articleService.createComment(articleId, request)

    @PutMapping("/{articleId}/comments/{commentId}")
    fun editComment(
        @PathVariable articleId: Article.ArticleIdentifier,
        @PathVariable commentId: Comment.CommentIdentifier,
        @RequestBody request: Map<String, String>,
    ): Map<String, Any> = articleService.editComment(articleId, commentId, request)

    @DeleteMapping("/{articleId}/comments/{commentId}")
    fun deleteComment(
        @PathVariable articleId: Article.ArticleIdentifier,
        @PathVariable commentId: Comment.CommentIdentifier,
    ) = articleService.deleteComment(articleId, commentId)
}
