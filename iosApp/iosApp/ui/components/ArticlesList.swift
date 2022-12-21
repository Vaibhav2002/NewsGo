//
//  ArticlesList.swift
//  iosApp
//
//  Created by Vaibhav Jaiswal on 21/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

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


//struct ArticlesList_Previews: PreviewProvider {
//    static var previews: some View {
//        ArticlesList([], {}, {})
//    }
//}
