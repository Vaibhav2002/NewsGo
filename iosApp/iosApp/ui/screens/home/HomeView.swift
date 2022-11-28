//
//  HomeView.swift
//  iosApp
//
//  Created by Vaibhav Jaiswal on 27/11/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct HomeView: View {
    
    @StateObject var viewModel = HomeViewModel()
    
    var body: some View {
        Text(viewModel.articles.randomElement()?.description ?? "")
            .font(.subheadline)
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
