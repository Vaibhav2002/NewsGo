//
//  SavedArticlesViewModel.swift
//  iosApp
//
//  Created by Vaibhav Jaiswal on 21/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

@available(iOS 15.0, *)
extension SavedArticlesScreen{
    @MainActor class SavedArticlesViewModel:ObservableObject{
        
        private var viewModel = CommonSavedArticlesViewModel(
            savedNewsRepo: AppModule.shared.savedNewsRepo,
            saveArticleUseCase: AppModule.shared.saveArticleUseCase,
            scope: nil
        )
        
        @Published private(set) var uiState = SavedArticlesScreenState(articles: [])
        
        private var disposableHandle:DisposableHandle?

        
        func collectUiState(){
            disposableHandle = viewModel.uiState.subscribe { state in
                if let state{
                    self.uiState = state
                }
            }
        }
        
        func onSaveToggled(article:Article){
            viewModel.onSaveToggled(article: article)
        }
        
        func dispose(){
            disposableHandle?.dispose()
        }
    }
}
