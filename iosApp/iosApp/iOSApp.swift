import SwiftUI
import shared

@available(iOS 15.0, *)
@main
struct iOSApp: App {
    let appModule = AppModule()
	var body: some Scene {
		WindowGroup {
			HomeScreen(appModule: appModule)
                .frame(maxWidth: .infinity, maxHeight: .infinity)
		}
	}
}
