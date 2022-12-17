import SwiftUI
import shared

@available(iOS 15.0, *)
@main
struct iOSApp: App {
    let appModule = AppModule()
	var body: some Scene {
		WindowGroup {
			HomeView(appModule: appModule)
                .frame(maxWidth: .infinity, maxHeight: .infinity)
		}
	}
}
