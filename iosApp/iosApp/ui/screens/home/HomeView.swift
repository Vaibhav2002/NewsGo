//
//  HomeView.swift
//  iosApp
//
//  Created by Vaibhav Jaiswal on 27/11/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

@available(iOS 15.0, *)
struct HomeView: View {
    
    @StateObject var viewModel = HomeViewModel()
    
    var body: some View {
        NavigationView{
            ScrollView{
                VStack{
                    TopicList(topics: viewModel.topics, selectedTopic: viewModel.topic) { selectedTopic in
                        viewModel.topic = selectedTopic
                    }.padding(.leading, 16)
                        .padding(.vertical, 16)
                    ArticlesList(articles: viewModel.articles) { article in
                        
                    }
                }
            }.navigationTitle(viewModel.topic.topic)
        }
    }
}

@available(iOS 15.0, *)
struct ArticlesList : View {
    
    var articles:[Article]
    var onArticleClick:(Article) -> Void
    
    var body:some View{
            LazyVStack{
                ForEach(articles, id: \.url){ article in
                    NewsItem(news: article)
                    
                    if article != articles.last {
                        Spacer()
                            .frame(height: 16)
                        Divider()
                        Spacer()
                            .frame(height: 16)
                    }
                }
            }
        
    }
}

@available(iOS 15.0, *)
struct TopicList : View{
    
    var topics:[Topic]
    var selectedTopic:Topic
    var onSelect:(Topic) -> Void
    
    var body: some View{
        ScrollView(.horizontal, showsIndicators: false){
            LazyHStack{
                ForEach(topics, id: \.topic){ topic in
                    TopicItem(topic: topic, isSelected: topic == selectedTopic){
                        onSelect(topic)
                    }
                    Spacer().frame(width: 8,height:0)
                }
            }
        }
    }
}

@available(iOS 15.0, *)
struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
