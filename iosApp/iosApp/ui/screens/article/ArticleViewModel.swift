//
//  ArticleViewModel.swift
//  iosApp
//
//  Created by Vaibhav Jaiswal on 17/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

@available(iOS 15.0, *)
extension ArticleScreen{
    @MainActor class ArticleViewModel:ObservableObject{
        
        private var appModule:AppModule?
        private var article:Article?
        
        private var viewModel:CommonArticleDetailViewModel?
        
        @Published private(set) var uiState = ArticleDetailScreenState(
            image: "",
            title: "",
            description: "",
            content: "",
            timeStamp: "",
            url: "",
            isSaved: false
        )
        
        func setDependencies(appModule: AppModule, article: Article){
            self.appModule = appModule
            self.article = article
            
            self.viewModel = CommonArticleDetailViewModel(
                article: article,
                saveArticleUseCase: appModule.saveArticleUseCase,
                scope: nil
            )
        }
        
        private var disposableHandle:DisposableHandle?
        
        @MainActor func collectUiState(){
            disposableHandle = viewModel?.uiState.subscribe { state in
                if let state {
                    self.uiState = state
                }
            }
        }
        
        func toggleLike() {
            viewModel?.toggleSave()
        }
        
        func dispose(){
            disposableHandle?.dispose()
        }
        
    }
}
