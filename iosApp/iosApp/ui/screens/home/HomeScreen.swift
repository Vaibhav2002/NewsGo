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
struct HomeScreen: View {
    
    
    @StateObject private var viewModel = HomeViewModel()
    @State private var isArticleSelected = false
    @State private var selectedArticle:Article? = nil
    @State private var isSavedButtonPressed = false
    @State private var isSearchButtonPressed = false
    
    var body: some View {
        NavigationView{
            ScrollView{
                VStack{
                    NavigationLink(
                        destination:ArticleScreen(article: selectedArticle),
                        isActive: $isArticleSelected
                    ){ EmptyView() }
                    
                    NavigationLink(destination: SavedArticlesScreen(), isActive: $isSavedButtonPressed){
                        EmptyView()
                    }
                    
                    NavigationLink(destination : SearchScreen(), isActive: $isSearchButtonPressed){
                        EmptyView()
                    }
                    
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
            .navigationBarItems(
                trailing: AppBarButtons(
                    onSaveClick: { isSavedButtonPressed = true },
                    onSearchClick: { isSearchButtonPressed = true }
                )
            )
            .navigationTitle(viewModel.uiState.topic.topic)
            .onAppear{
                viewModel.collectUiState()
            }
            .onDisappear{
                viewModel.onDispose()
            }
            .refreshable{
                viewModel.onRefresh()
            }
            
        }
    }
}

@available(iOS 15.0, *)
struct AppBarButtons : View{
    
    let onSaveClick:()->Void
    let onSearchClick:()->Void

    
    var body: some View{
        HStack{
            SavedButton(onPress: onSaveClick)
            Spacer().frame(width: 8)
            SearchButton(onPress: onSearchClick)
        }
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

@available(iOS 15.0, *)
struct SavedButton : View{
    
    let onPress:()->Void
    
    var body: some View {
        Button(action: onPress){
            Image(systemName: "heart")
        }
    }
}

@available(iOS 15.0, *)
struct SearchButton : View{
    
    let onPress:()->Void
    
    var body: some View {
        Button(action: onPress){
            Image(systemName: "magnifyingglass")
        }
    }
}

//@available(iOS 15.0, *)
//struct HomeView_Previews: PreviewProvider {
//    static var previews: some View {
//        HomeView()
//    }
//}
