//
//  SearchScreen.swift
//  iosApp
//
//  Created by Vaibhav Jaiswal on 26/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

@available(iOS 15.0, *)
struct SearchScreen: View {
    
    @StateObject private var viewModel = SearchViewModel()
    @State private var selectedArticle:Article?
    @State private var isArticleSelected = false
    
    @State private var searchQuery = ""
    
    var body: some View {
        let uiState = viewModel.state
        ScrollView{
            NavigationLink(
                destination: ArticleScreen(article: selectedArticle),
                isActive: $isArticleSelected
            ) { EmptyView() }
            ZStack(alignment: .center){
                ArticlesList(
                    articles: uiState.articles,
                    onArticleSaveToggled: { article in viewModel.onSaveToggled(article: article) },
                    onArticleClick: { article in
                        selectedArticle = article
                        isArticleSelected = true
                    }
                )
            }
        }
        .navigationTitle("Search")
        .searchable(text: $searchQuery).onChange(of: searchQuery){ query in
            viewModel.onSearchQueryChanged(query: query)
        }
        .onAppear { viewModel.startObserving() }
        .onDisappear { viewModel.dispose() }
    }
}

//struct SearchScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        SearchScreen()
//    }
//}
