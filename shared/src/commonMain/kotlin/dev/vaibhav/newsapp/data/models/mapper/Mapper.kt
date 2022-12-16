package dev.vaibhav.newsapp.data.models.mapper

import database.ArticleEntity
import dev.vaibhav.newsapp.data.models.remote.ArticleDto
import dev.vaibhav.newsapp.domain.Topic
import dev.vaibhav.newsapp.utils.serialize.SourceSerializer

val sourceSerializer = SourceSerializer()
fun ArticleDto.toArticleEntity(topic:Topic) = ArticleEntity(
    author = author ?: "",
    content = content ?: "",
    description = description ?: "",
    published_at = publishedAt,
    source = source?.let(sourceSerializer::serialize),
    title = title ?: "",
    url = url ?: "",
    image_url = urlToImage ?: "",
    topic = topic.topic,
    id = 0
)

fun List<ArticleDto>.toArticlesEntities(topic: Topic) = map { it.toArticleEntity(topic) }