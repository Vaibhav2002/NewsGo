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
    @State var isArticleSelected = false
    @State var selectedArticle:Article? = nil
    
    var body: some View {
        NavigationView{
            ScrollView{
                VStack{
                    NavigationLink(
                        destination:ArticleView(article: selectedArticle),
                        isActive: $isArticleSelected
                    ){ EmptyView() }
                    TopicList(topics: viewModel.topics, selectedTopic: $viewModel.topic)
                        .padding([.leading, .vertical], 16)
                    ArticlesList(articles: viewModel.articles) { article in
                        isArticleSelected = true
                        selectedArticle = article
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
                    NewsItem(news: article, onArticleClick: onArticleClick)
                    
                    if article != articles.last {
                        Spacer()
                            .frame(height: 16)
                        Spacer()
                            .frame(height: 16)
                    }
                }
            }.listRowSeparator(.visible)
        
    }
}

@available(iOS 15.0, *)
struct TopicList : View{
    
    var topics:[Topic]
    @Binding var selectedTopic:Topic
    
    var body: some View{
        ScrollView(.horizontal, showsIndicators: false){
            LazyHStack{
                ForEach(topics, id: \.topic){ topic in
                    TopicItem(topic: topic, isSelected: topic == selectedTopic){
                        selectedTopic = topic
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
