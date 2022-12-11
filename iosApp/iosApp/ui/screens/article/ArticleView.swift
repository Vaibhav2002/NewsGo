//
//  ArticleView.swift
//  iosApp
//
//  Created by Vaibhav Jaiswal on 11/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

@available(iOS 15.0, *)
struct ArticleView: View {
    
    var article:Article?
    
    var body: some View {
        VStack{
            ArticleCover(title:article?.title ?? "", image:article?.urlToImage ?? "")
                .cornerRadius(8)
            Text(article?.description_ ?? "")
                .font(.callout)
                .frame(maxWidth: .infinity)
                .padding([.horizontal, .top], 8)
            Spacer().frame(height: 8)
            Text(article?.content ?? "")
                .font(.caption)
                .padding(.horizontal, 8)
                .foregroundColor(Color.gray)
        }
        .padding(4)
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
        .navigationBarTitle(article?.title ?? "", displayMode: .inline)
    }
}

@available(iOS 15.0, *)
struct ArticleCover:View{
    
    var title:String
    var image:String
    
    var body: some View {
        ZStack{
            AsyncImage(
                url:URL(string: image),
                content: { image in image.resizable() },
                placeholder: {}
            )
            Text(title)
                .padding(16)
                .padding(.bottom, 8)
                .font(.title)
                .lineLimit(2)
                .foregroundColor(Color.white)
                .frame(maxWidth:.infinity, maxHeight: .infinity/2,alignment: .bottomLeading)
                .background(
                    LinearGradient(colors: [Color.clear, Color.black], startPoint: .top, endPoint: .bottom)
                )
        }
        .frame(maxWidth: .infinity)
        .aspectRatio(CGSize(width:4, height:3), contentMode: .fit)
        
    }
}

@available(iOS 15.0, *)
struct ArticleCover_Previews: PreviewProvider {
    static var previews: some View {
        ArticleCover(
            title: "Lorem Ipsum Dolor Si Amet Lorem Ipsum Dolor Si",
            image:"https://www.gannett-cdn.com/presto/2022/12/09/USAT/e83cef6a-9bbb-4f2b-9b5f-5fd3041fa5d2-XXX_RS_40790.JPG?auto=webp&crop=3590,2019,x0,y347&format=pjpg&width=1200"
        )
    }
}
