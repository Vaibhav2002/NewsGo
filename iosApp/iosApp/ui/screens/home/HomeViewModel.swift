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
extension HomeView{
    @MainActor class HomeViewModel:ObservableObject {
        
        private let viewModel = CommonHomeViewModel(
            newsRepo: AppModule().articleRepo,
            scope:nil
        )
        
        @Published var uiState = HomeScreenState(
            articles: [],
            topic: Topic.Headlines(),
            isLoading: false,
            error: nil
        )
        
        let topics = [Topic.Headlines(), Topic.Sports(),
                      Topic.Politics(),Topic.Entertainment(),
                      Topic.Technology()]
        
        private var disposableHandle:DisposableHandle?
        
        @MainActor
        func collectUiState(){
            disposableHandle = viewModel.uiState.subscribe { state in
                if let state {
                    self.uiState = state
                }
            }
        }
        
        func onTopicPressed(topic:Topic){
            viewModel.onTopicChange(topic: topic)
        }
        
        func onDispose(){
            disposableHandle?.dispose()
        }
    }
}
