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
    
    let appModule:AppModule
    
    @StateObject private var viewModel = HomeViewModel()
    @State private var isArticleSelected = false
    @State private var selectedArticle:Article? = nil
    
    var body: some View {
        NavigationView{
            ScrollView{
                VStack{
                    NavigationLink(
                        destination:ArticleView(appModule: appModule, articleId: selectedArticle?.id ?? 0),
                        isActive: $isArticleSelected
                    ){ EmptyView() }
                    
                    TopicList(
                        topics: viewModel.topics,
                        selectedTopic: viewModel.uiState.topic,
                        onTopicChange: { topic in viewModel.onTopicPressed(topic: topic)}
                    ).padding([.leading, .vertical], 16)
                    
                    ArticlesList(
                        articles: viewModel.uiState.articles,
                        onArticleSaveToggled: {
                            article in viewModel.onArticleLikeToggled(article: article)
                        },
                        onArticleClick: { article in
                            isArticleSelected = true
                            selectedArticle = article
                        }
                    )
                }
            }
            .navigationTitle(viewModel.uiState.topic.topic)
            .onAppear{
                viewModel.setAppModule(appModule: appModule)
                viewModel.collectUiState()
            }
            .onDisappear{
                viewModel.onDispose()
            }
            
        }
    }
}

@available(iOS 15.0, *)
struct ArticlesList : View {
    
    var articles:[Article]
    var onArticleSaveToggled:(Article) -> Void
    var onArticleClick:(Article) -> Void
    
    var body:some View{
            LazyVStack{
                ForEach(articles, id: \.url){ article in
                    NewsItem(
                        news: article,
                        onArticleClick: onArticleClick,
                        onArticleLikeToggled: onArticleSaveToggled
                    )
                    
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
    
    let topics:[Topic]
    let selectedTopic:Topic
    let onTopicChange:(Topic)->Void
    
    var body: some View{
        ScrollView(.horizontal, showsIndicators: false){
            LazyHStack{
                ForEach(topics, id: \.topic){ topic in
                    TopicItem(topic: topic, isSelected: topic == selectedTopic){
                        onTopicChange(topic)
                    }
                    Spacer().frame(width: 8,height:0)
                }
            }
        }
    }
}

//@available(iOS 15.0, *)
//struct HomeView_Previews: PreviewProvider {
//    static var previews: some View {
//        HomeView()
//    }
//}
