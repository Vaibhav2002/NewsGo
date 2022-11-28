package dev.vaibhav.newsapp.domain.mappers

import dev.vaibhav.newsapp.data.models.ArticleDto
import dev.vaibhav.newsapp.data.models.SourceDto
import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.models.Source
import dev.vaibhav.newsapp.utils.DateTimeUtil

fun ArticleDto.toArticle() = Article(
    author = author ?: "",
    content = content ?: "",
    description = description ?: "",
    timeStamp = DateTimeUtil.toLocalDateTime(publishedAt),
    source = source?.toSource(),
    title = title ?: "",
    url = url ?: "",
    urlToImage = urlToImage ?: ""
)

fun SourceDto.toSource() = Source(
    id = id ?: "",
    name = name ?: ""
)

fun List<ArticleDto>.toArticles() = map { it.toArticle() }