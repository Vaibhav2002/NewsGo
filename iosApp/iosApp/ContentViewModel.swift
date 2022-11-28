//
//  ContentViewModel.swift
//  iosApp
//
//  Created by Vaibhav Jaiswal on 25/11/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

extension ContentView{
    @MainActor class ContentViewModel:ObservableObject{
        
        @Published var text = ""
        
        init(){
            Greeting().greeting { response, error in
                self.text = response!
            }
        }
    }
}
