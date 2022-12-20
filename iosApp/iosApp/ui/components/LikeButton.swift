//
//  LikeButton.swift
//  iosApp
//
//  Created by Vaibhav Jaiswal on 19/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

@available(iOS 15.0, *)
struct LikeButton<Content:Shape>: View {
    
    let shape:Content
    let isLiked:Bool
    let onPress:()->Void
    
    var body: some View {
        Button(action:onPress) {
            let tint = isLiked ? Color.red : Color.gray
            Image(systemName: "heart.fill")
                .foregroundColor(tint)
                .frame(maxWidth: .infinity/2, maxHeight: .infinity/2, alignment: .center)
        }.background(.thinMaterial, in: shape)
    }
}

@available(iOS 15.0, *)
struct LikeButton_Previews: PreviewProvider {
    static var previews: some View {
        LikeButton(shape:Circle(), isLiked: true, onPress: {})
    }
}
