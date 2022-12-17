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
        private var articleId:Int64?
        
        private var viewModel:CommonArticleDetailViewModel?
        
        @Published private(set) var uiState = ArticleDetailScreenState(
            image: "",
            title: "",
            description: "",
            content: "",
            timeStamp: "",
            url: ""
        )
        
        func setDependencies(appModule: AppModule, articleId: Int64){
            self.appModule = appModule
            self.articleId = articleId
            
            self.viewModel = CommonArticleDetailViewModel(
                articleId: articleId,
                newsRepo: appModule.articleRepo,
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
        
        func dispose(){
            disposableHandle?.dispose()
        }
    }
}
