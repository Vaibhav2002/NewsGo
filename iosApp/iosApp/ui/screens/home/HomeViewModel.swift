//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Vaibhav Jaiswal on 27/11/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

@available(iOS 15.0, *)
extension HomeScreen{
    @MainActor class HomeViewModel:ObservableObject {
        
        private var appModule:AppModule?
        private var viewModel:CommonHomeViewModel?
        
        func setAppModule(appModule: AppModule){
            self.appModule = appModule
            self.viewModel = CommonHomeViewModel(
                newsRepo: appModule.articleRepo,
                saveArticleUseCase: appModule.saveArticleUseCase,
                scope:nil
            )
        }
        
        @Published var uiState = HomeScreenState(
            articles: [],
            topic: Topic.Headlines(),
            isLoading: false,
            error: nil,
            isRefreshing: false
        )
        
        let topics = [Topic.Headlines(), Topic.Sports(),
                      Topic.Politics(),Topic.Entertainment(),
                      Topic.Technology()]
        
        private var disposableHandle:DisposableHandle?
        
        @MainActor
        func collectUiState(){
            disposableHandle = viewModel?.uiState.subscribe { state in
                if let state {
                    self.uiState = state
                }
            }
        }
        
        func onTopicPressed(topic:Topic){
            viewModel?.onTopicChange(topic: topic)
        }
        
        func onArticleLikeToggled(article:Article){
            viewModel?.onSavePress(article: article)
        }
        
        func onRefresh(){
            viewModel?.onRefresh()
        }
        
        func onDispose(){
            disposableHandle?.dispose()
        }
    }
}
