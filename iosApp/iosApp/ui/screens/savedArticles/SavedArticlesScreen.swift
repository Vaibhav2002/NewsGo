//
//  SavedArticlesScreen.swift
//  iosApp
//
//  Created by Vaibhav Jaiswal on 21/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

@available(iOS 15.0, *)
struct SavedArticlesScreen: View {
    

    @StateObject private var viewModel = SavedArticlesViewModel()
    
    @State private var isArticleSelected = false
    @State private var selectedArticle:Article? = nil
    
    var body: some View {
        let uiState = viewModel.uiState
            ScrollView{
                VStack{
                    NavigationLink(
                        destination: ArticleScreen(article: selectedArticle),
                        isActive: $isArticleSelected
                    ) { EmptyView() }
                    ArticlesList(
                        articles: uiState.articles,
                        onArticleSaveToggled: { article in viewModel.onSaveToggled(article: article)},
                        onArticleClick: { article in
                            selectedArticle = article
                            isArticleSelected = true
                        }
                    )
                }
            }
            .onAppear{
                viewModel.collectUiState()
            }
            .onDisappear{
                viewModel.dispose()
            }
        .navigationTitle("Saved Articles")
        .navigationBarTitleDisplayMode(.large)
        
    }

}

//struct SavedArticlesScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        SavedArticlesScreen()
//    }
//}
