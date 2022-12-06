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
        
        private var newsRepo:NewsRepo = NewsRepoImpl(dataSource:NewsDataSource())
        
        @Published private(set) var articles = [Article]()
        
        var topics = [
                Topic.Headlines(), Topic.Sports(), Topic.Politics(),
                Topic.Entertainment(), Topic.Technology()
            ]
        
        @Published var topic:Topic = Topic.Headlines() {
            didSet{
                (topic === Topic.Headlines()) ? getHeadlines() :getNewsByTopic(topic:topic)
            }
        }
        
        init(){
            getHeadlines()
        }
        
        private func getHeadlines(){
            newsRepo.getTopHeadlines(country:"in", completionHandler: { articles, error in
                self.articles = articles ?? []
            })
        }
        
        private func getNewsByTopic(topic:Topic){
            newsRepo.getNewsByTopic(
                topic: topic,
                completionHandler: { articles, error in
                    self.articles =  articles ?? []
                }
            )
        }
        
    }
}
