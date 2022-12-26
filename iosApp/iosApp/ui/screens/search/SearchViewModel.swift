//
//  SearchViewModel.swift
//  iosApp
//
//  Created by Vaibhav Jaiswal on 26/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

@available(iOS 15.0, *)
extension SearchScreen{
    @MainActor class SearchViewModel : ObservableObject {
        
        private let viewModel = CommonSearchViewModel(
            newsRepo: AppModule.shared.articleRepo,
            savedNewsRepo: AppModule.shared.savedNewsRepo,
            saveArticleUseCase: AppModule.shared.saveArticleUseCase,
            scope: nil
        )
        
        @Published private(set) var state = SearchScreenState(
            searchQuery: "",
            articles: [],
            error: nil,
            isLoading: false
        )
        
        private var disposableHandle:DisposableHandle?
        
        func startObserving(){
            viewModel.uiState.subscribe { state in
                if let state {
                    self.state = state
                }
            }
        }
        
        func onSearchQueryChanged(query:String){
            viewModel.onSearchQueryChanged(query: query)
        }
        
        func onSaveToggled(article:Article){
            viewModel.onSaveToggled(article: article)
        }
        
        func dispose(){
            disposableHandle?.dispose()
        }
    }
}
