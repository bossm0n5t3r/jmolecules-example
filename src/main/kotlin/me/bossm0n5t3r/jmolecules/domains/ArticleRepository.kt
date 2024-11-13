package me.bossm0n5t3r.jmolecules.domains

import org.jmolecules.ddd.integration.AssociationResolver
import org.jmolecules.ddd.types.Repository
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository :
    Repository<Article, Article.ArticleIdentifier>,
    JpaRepository<Article, Article.ArticleIdentifier>,
    AssociationResolver<Article, Article.ArticleIdentifier> {
    fun findAllByStatus(status: Status): List<Article>

    fun findBySlug(slug: Slug): Article?
}
