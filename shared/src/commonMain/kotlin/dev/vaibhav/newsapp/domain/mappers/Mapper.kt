package dev.vaibhav.newsapp.domain.mappers

import database.ArticleEntity
import database.SavedArticle
import dev.vaibhav.newsapp.data.models.remote.ArticleDto
import dev.vaibhav.newsapp.data.models.remote.SourceDto
import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.models.Saved
import dev.vaibhav.newsapp.domain.models.Source
import dev.vaibhav.newsapp.domain.models.Topic
import dev.vaibhav.newsapp.utils.DateTimeUtil
import dev.vaibhav.newsapp.utils.serialize.SourceSerializer

val sourceSerializer = SourceSerializer()

fun SourceDto.toSource() = Source(
    id = id,
    name = name
)

fun ArticleDto.toArticle() = Article(
    author = author ?: "",
    content = content ?: "",
    description = description ?: "",
    timeStamp = DateTimeUtil.toLocalDateTime(publishedAt),
    source = source?.toSource(),
    title = title ?: "",
    url = url ?: "",
    urlToImage = urlToImage ?: "",
    topic = Topic.Headlines,
    saved = null
)

fun ArticleEntity.toArticle(saved: Saved? = null) = Article(
    author = author ?: "",
    content = content ?: "",
    description = description ?: "",
    timeStamp = DateTimeUtil.toLocalDateTime(published_at),
    source = source?.let(sourceSerializer::deSerialize),
    title = title ?: "",
    url = url,
    urlToImage = image_url ?: "",
    topic = Topic.fromTopic(topic),
    saved = saved
)

fun List<ArticleEntity>.toArticles(
    saved: Map<String, Saved>
) = map { it.toArticle(saved[it.url]) }

fun Article.toArticleEntity() = ArticleEntity(
    author = author,
    content = content,
    description = description,
    published_at = DateTimeUtil.toInstant(timeStamp),
    source = source?.let(sourceSerializer::serialize),
    title = title,
    url = url,
    image_url = urlToImage,
    topic = topic.topic,
)

fun SavedArticle.toArticle() = Article(
    author = author ?: "",
    content = content ?: "",
    description = description ?: "",
    timeStamp = DateTimeUtil.toLocalDateTime(published_at),
    source = source?.let(sourceSerializer::deSerialize),
    title = title ?: "",
    url = url,
    urlToImage = image_url ?: "",
    topic = Topic.fromTopic(topic),
    saved = Saved(id, true, DateTimeUtil.toLocalDateTimeFromMillis(savedTimestamp))
)
