//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Vaibhav Jaiswal on 27/11/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

extension HomeView{
    @MainActor class HomeViewModel:ObservableObject {
        
        private var newsRepo:NewsRepo = NewsRepoImpl(dataSource:NewsDataSource())
        
        @Published private(set) var articles = [Article]()
        
        @Published var topic:Topic? = nil {
            didSet{
                (topic == nil) ? getHeadlines() : getNewsByTopic(topic:topic!)
            }
        }
        
        init(){
            getHeadlines()
        }
        
        func getHeadlines(){
            newsRepo.getTopHeadlines(country:"in", completionHandler: { articles, error in
                self.articles = articles ?? []
            })
        }
        
        func getNewsByTopic(topic:Topic){
            newsRepo.getNewsByTopic(
                topic: topic,
                completionHandler: { articles, error in
                    self.articles =  articles ?? []
                }
            )
        }
        
    }
}
