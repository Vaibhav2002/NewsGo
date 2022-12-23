package dev.vaibhav.newsapp.data.models.mapper

import database.ArticleEntity
import dev.vaibhav.newsapp.data.models.remote.ArticleDto
import dev.vaibhav.newsapp.domain.models.Topic
import dev.vaibhav.newsapp.utils.serialize.SourceDtoSerializer

val sourceDtoSerializer = SourceDtoSerializer()
fun ArticleDto.toArticleEntity(topic: Topic) = ArticleEntity(
    author = author ?: "",
    content = content ?: "",
    description = description ?: "",
    published_at = publishedAt,
    source = source?.let(sourceDtoSerializer::serialize),
    title = title ?: "",
    url = url ?: "",
    image_url = urlToImage ?: "",
    topic = topic.topic
)

fun List<ArticleDto>.toArticlesEntities(topic: Topic) = map { it.toArticleEntity(topic) }