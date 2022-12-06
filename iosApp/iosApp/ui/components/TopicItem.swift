//
//  TopicItem.swift
//  iosApp
//
//  Created by Vaibhav Jaiswal on 06/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

@available(iOS 15.0, *)
struct TopicItem: View {
    
    var topic:Topic
    var isSelected:Bool
    var onClick:()->Void
    
    var body: some View {
        if isSelected{
            Button(topic.topic, action: onClick)
                .buttonStyle(.bordered)
                .foregroundColor(Color.green)
        }
        else{
            Button(topic.topic, action: onClick).buttonStyle(.bordered)
        }
    }
}

@available(iOS 15.0, *)
struct TopicItem_Previews: PreviewProvider {
    static var previews: some View {
        TopicItem(topic:Topic.Headlines(), isSelected: true){
            
        }
    }
}
