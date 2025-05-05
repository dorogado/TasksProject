import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import screens.ProjectScreen
import screens.ProjectsScreen
import screens.SignInScreen
import screens.SignUpScreen

sealed class NavHost(val screen: String) {
    data object SignUpScreen: NavHost("SignUpScreen")
    data object SignInScreen: NavHost("SignInScreen")
    data object ProjectsScreen: NavHost("ProjectsScreen")
    data class ProjectScreen(val project: String = ""): NavHost("ProjectScreen")
}

@Composable
fun NavController() {
    var currentScreen by remember { mutableStateOf<NavHost>(NavHost.ProjectsScreen) }
    when (currentScreen) {
        NavHost.SignInScreen -> SignInScreen {currentScreen = NavHost.SignUpScreen}
        NavHost.ProjectsScreen -> ProjectsScreen(onGoToProject = {currentScreen = NavHost.ProjectScreen()}, onSignOut = { currentScreen = NavHost.SignUpScreen })
        NavHost.SignUpScreen -> SignUpScreen {currentScreen = NavHost.SignInScreen}
        is NavHost.ProjectScreen -> ProjectScreen {currentScreen = NavHost.ProjectsScreen}
    }
}
