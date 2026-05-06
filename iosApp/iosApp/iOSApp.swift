import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        KoinInitializer().initialize()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}