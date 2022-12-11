import SwiftUI

@available(iOS 15.0, *)
@main
struct iOSApp: App {
	var body: some Scene {
		WindowGroup {
			HomeView()
                .frame(maxWidth: .infinity, maxHeight: .infinity)
		}
	}
}
