package dev.vaibhav.newsapp.domain.usecases

import dev.vaibhav.newsapp.domain.models.Article
import dev.vaibhav.newsapp.domain.repo.SavedNewsRepo

class SaveArticleUseCase(private val savedNewsRepo: SavedNewsRepo) {

    suspend operator fun invoke(article: Article){
        if(article.saved?.isSaved == true)
            savedNewsRepo.unSaveArticle(article)
        else savedNewsRepo.saveArticle(article)
    }
}