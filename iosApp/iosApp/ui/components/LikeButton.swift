//
//  LikeButton.swift
//  iosApp
//
//  Created by Vaibhav Jaiswal on 19/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

@available(iOS 15.0, *)
struct LikeButton: View {
    
    let isLiked:Bool
    let onPress:()->Void
    
    var body: some View {
        Button(action:onPress) {
            let imageName = isLiked ? "heart.fill" : "heart"
            let tint = isLiked ? Color.red : Color.gray
            Image(systemName: imageName)
                .foregroundColor(tint)
                .padding(4)
        }
        .frame(width:48, height:48)
        .background(.ultraThinMaterial, in: Circle())
    }
}

@available(iOS 15.0, *)
struct LikeButton_Previews: PreviewProvider {
    static var previews: some View {
        LikeButton(isLiked: true, onPress: {})
    }
}
