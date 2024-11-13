package me.bossm0n5t3r.jmolecules.controllers

import me.bossm0n5t3r.jmolecules.domains.Slug
import me.bossm0n5t3r.jmolecules.domains.Status
import me.bossm0n5t3r.jmolecules.services.ArticleService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
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
    fun findBySlug(
        @PathVariable slug: Slug,
    ): Map<String, Any> = articleService.findBySlug(slug)

    @GetMapping
    fun findAll(): List<Map<String, Any>> = articleService.findAll()

    @GetMapping("/status/{status}")
    fun filterByStatus(
        @PathVariable status: Status,
    ): List<Map<String, Any>> = articleService.filterByStatus(status)

    @DeleteMapping("/{slug}")
    fun deleteArticle(
        @PathVariable slug: Slug,
    ) {
        articleService.delete(slug)
    }
}
