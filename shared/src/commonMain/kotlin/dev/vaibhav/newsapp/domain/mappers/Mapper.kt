package dev.vaibhav.newsapp.domain.mappers

import dev.vaibhav.newsapp.data.models.local.ArticleEntity
import dev.vaibhav.newsapp.domain.Topic
import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.models.Source
import dev.vaibhav.newsapp.utils.DateTimeUtil
import dev.vaibhav.newsapp.utils.Serializer

fun ArticleEntity.toArticle() = Article(
    author = author,
    content = content,
    description = description,
    timeStamp = DateTimeUtil.toLocalDateTime(publishedAt),
    source = source?.let(Serializer::deserialize),
    title = title,
    url = url,
    urlToImage = urlToImage,
    topic = Topic.fromTopic(topic)
)

fun List<ArticleEntity>.toArticles() = map { it.toArticle() }