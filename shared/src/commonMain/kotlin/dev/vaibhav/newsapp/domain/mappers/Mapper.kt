package dev.vaibhav.newsapp.domain.mappers

import database.ArticleEntity
import dev.vaibhav.newsapp.domain.Topic
import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.utils.DateTimeUtil
import dev.vaibhav.newsapp.utils.serialize.JsonSerializer
import dev.vaibhav.newsapp.utils.serialize.SourceSerializer

val sourceSerializer = SourceSerializer()
fun ArticleEntity.toArticle() = Article(
    author = author ?: "",
    content = content ?: "",
    description = description ?: "",
    timeStamp = DateTimeUtil.toLocalDateTime(published_at),
    source = source?.let(sourceSerializer::deSerialize),
    title = title ?: "",
    url = url,
    urlToImage = image_url ?: "",
    topic = Topic.fromTopic(topic)
)

fun List<ArticleEntity>.toArticles() = map { it.toArticle() }