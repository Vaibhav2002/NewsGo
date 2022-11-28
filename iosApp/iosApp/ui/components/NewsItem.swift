//
//  NewsItem.swift
//  iosApp
//
//  Created by Vaibhav Jaiswal on 28/11/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NewsItem: View {
    
    let news:Article
    
    var body: some View {
        VStack{
            Image
            Text(news.title)
                .font(.title)
                .foregroundColor(Color.black)
            Spacer()
            Text(news.description)
                .font(.subheadline)
                .foregroundColor(Color.gray)
        }
        
    }
}

struct NewsItem_Previews: PreviewProvider {
    static var previews: some View {
        NewsItem(news:Article())
    }
}
