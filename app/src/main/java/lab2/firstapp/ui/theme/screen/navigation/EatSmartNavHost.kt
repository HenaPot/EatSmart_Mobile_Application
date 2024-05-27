package lab2.firstapp.ui.theme.screen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import lab2.firstapp.ui.theme.screen.LoginDestination
import lab2.firstapp.ui.theme.screen.LoginScreen
import lab2.firstapp.ui.theme.screen.LoginScreenWithTopBar
import lab2.firstapp.ui.theme.screen.PreferenceScreen
import lab2.firstapp.ui.theme.screen.PreferenceScreenWithTopBar
import lab2.firstapp.ui.theme.screen.PreferencesDestination
import lab2.firstapp.ui.theme.screen.ProfileDestination
import lab2.firstapp.ui.theme.screen.ProfileScreen
import lab2.firstapp.ui.theme.screen.ProfileScreenWithTopBar
import lab2.firstapp.ui.theme.screen.RegistrationDestination
import lab2.firstapp.ui.theme.screen.RegistrationScreen
import lab2.firstapp.ui.theme.screen.RegistrationScreenWithTopBar

@Composable
fun EatSmartNavHost(
    navController: NavHostController
){
    NavHost(navController = navController, startDestination = LoginDestination.route){
        composable(route = LoginDestination.route){
            LoginScreenWithTopBar(
                context = navController.context,
                navigateToRegister ={ navController.navigate(RegistrationDestination.route) },
                navigateToProfilePage = { navController.navigate("${ProfileDestination.route}/${it}") }
            )
        }
        composable(route = RegistrationDestination.route){
            RegistrationScreenWithTopBar(
                navigateToLogin = { navController.navigate(LoginDestination.route) },
                navigateToProfilePage = { navController.navigate("${ProfileDestination.route}/${it}") }
            )
        }
        composable(
            route = ProfileDestination.routeWithArgs,
            arguments = listOf(navArgument(ProfileDestination.userIdArg) {
                type = NavType.IntType })
        ) {
            ProfileScreenWithTopBar(
                navigateBack = { navController.navigateUp() },
                navigateToPreferences = {navController.navigate("${PreferencesDestination.route}/${it}")}
                )
        }
        composable(
            route = PreferencesDestination.routeWithArgs,
            arguments = listOf(navArgument(PreferencesDestination.userIdArg){
                type = NavType.IntType })
        ){
            PreferenceScreenWithTopBar(
                navigateBack = {navController.navigateUp()}
            )
        }


    }
}
