//
//  NewsItem.swift
//  iosApp
//
//  Created by Vaibhav Jaiswal on 28/11/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

@available(iOS 15.0, *)
struct NewsItem: View {
    
    let news:Article
    let onArticleClick:(Article) -> Void
    let onArticleLikeToggled:(Article) -> Void
    
    var body: some View {
        VStack{
            ZStack(alignment: .topTrailing){
                AsyncImage(
                    url:URL(string: news.urlToImage),
                    content: { image in
                        image.resizable()
                            .aspectRatio(CGSize(width:16, height:9), contentMode: .fill)
                            .frame(maxWidth: .infinity)
                    },
                    placeholder: {
                        Image("image_placeholder")
                            .resizable()
                            .aspectRatio(CGSize(width:16, height:9), contentMode: .fill)
                            .frame(maxWidth: .infinity)
                    }
                )
                LikeButton(isLiked: news.saved?.isSaved == true){
                    onArticleLikeToggled(news)
                }
                .padding([.trailing, .top], 8)
            }
            
            Text(news.title)
                .font(.title2)
                .lineLimit(1)
                .foregroundColor(Color.black)
                .frame(maxWidth: .infinity)
                .padding(.horizontal, 16)
                .padding(.bottom, 2)
            Text(news.content)
                .font(.callout)
                .lineLimit(4)
                .frame(maxWidth: .infinity)
                .foregroundColor(Color.gray)
                .padding(.horizontal, 16)
            Spacer().frame(height: 24)
            HStack{
                Spacer()
                Text(DateTimeUtil().formatDateTime(dateTime: news.timeStamp))
                    .foregroundColor(Color.gray)
                    .padding(.trailing, 16)
                    .font(.caption2)
            }
            
        }.onTapGesture { onArticleClick(news) }
        
    }
}

//@available(iOS 15.0, *)
//struct NewsItem_Previews: PreviewProvider {
//    static var previews: some View {
//        NewsItem(news:Article())
//    }
//}
