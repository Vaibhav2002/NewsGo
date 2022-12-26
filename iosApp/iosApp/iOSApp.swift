import SwiftUI
import shared

@available(iOS 15.0, *)
@main
struct iOSApp: App {
	var body: some Scene {
		WindowGroup {
			HomeScreen()
                .frame(maxWidth: .infinity, maxHeight: .infinity)
		}
	}
}
