package dev.vaibhav.newsapp.data.models.mapper

import dev.vaibhav.newsapp.data.models.local.ArticleEntity
import dev.vaibhav.newsapp.data.models.remote.ArticleDto
import dev.vaibhav.newsapp.data.models.remote.SourceDto
import dev.vaibhav.newsapp.domain.Topic
import dev.vaibhav.newsapp.utils.Serializer

fun ArticleDto.toArticleEntity(topic:Topic) = ArticleEntity(
    author = author ?: "",
    content = content ?: "",
    description = description ?: "",
    publishedAt = publishedAt,
    source = source?.let(Serializer::serialize),
    title = title ?: "",
    url = url ?: "",
    urlToImage = urlToImage ?: "",
    topic = topic.topic
)

fun List<ArticleDto>.toArticlesEntities(topic: Topic) = map { it.toArticleEntity(topic) }