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
extension ArticleView{
    @MainActor class ArticleViewModel:ObservableObject{
        
        private var appModule:AppModule?
        private var articleUrl:String?
        
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
        
        func setDependencies(appModule: AppModule, articleUrl: String){
            self.appModule = appModule
            self.articleUrl = articleUrl
            
            self.viewModel = CommonArticleDetailViewModel(
                articleUrl: articleUrl,
                newsRepo: appModule.articleRepo,
                savedNewsRepo: appModule.savedNewsRepo,
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
