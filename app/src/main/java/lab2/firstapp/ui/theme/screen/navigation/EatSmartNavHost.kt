package lab2.firstapp.ui.theme.screen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import lab2.firstapp.ui.theme.screen.BrowseMealsDestination
import lab2.firstapp.ui.theme.screen.BrowseMealsScreenWithAppBar
import lab2.firstapp.ui.theme.screen.CalorieScreenWithAppBar
import lab2.firstapp.ui.theme.screen.CaloriesDestination
import lab2.firstapp.ui.theme.screen.LoginDestination
import lab2.firstapp.ui.theme.screen.LoginScreenWithTopBar
import lab2.firstapp.ui.theme.screen.MealDestination
import lab2.firstapp.ui.theme.screen.MealScreenWithAppBar
import lab2.firstapp.ui.theme.screen.PreferenceScreenWithTopBar
import lab2.firstapp.ui.theme.screen.PreferencesDestination
import lab2.firstapp.ui.theme.screen.ProfileDestination
import lab2.firstapp.ui.theme.screen.ProfileScreenWithTopBar
import lab2.firstapp.ui.theme.screen.RegistrationDestination
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
                navigateToPreferences = {navController.navigate("${PreferencesDestination.route}/${it}")},
                navigateToBrowseMealScreen = {navController.navigate("${BrowseMealsDestination.route}/${it}")},
                navigateToCaloriesScreen = {navController.navigate("${CaloriesDestination.route}/${it}")},
                logOut = {navController.navigate(LoginDestination.route)}
                )
        }
        composable(
            route = PreferencesDestination.routeWithArgs,
            arguments = listOf(navArgument(PreferencesDestination.userIdArg){
                type = NavType.IntType })
        ){
            PreferenceScreenWithTopBar(
                navigateBack = {navController.navigateUp()},
                navigateToBrowseMealScreen = {navController.navigate("${BrowseMealsDestination.route}/${it}")},
                navigateToCaloriesScreen = {navController.navigate("${CaloriesDestination.route}/${it}")},
                navigateToProfileScreen = { navController.navigate("${ProfileDestination.route}/${it}") },
                logOut = {navController.navigate(LoginDestination.route)}
            )
        }

        composable(
            route = BrowseMealsDestination.routeWithArgs,
            arguments = listOf(navArgument(BrowseMealsDestination.userIdArg) {
                type = NavType.IntType
            })
        ){
            BrowseMealsScreenWithAppBar(
                navigateBack = { navController.navigateUp() },
                navigateToBrowseMealScreen = {},
                //ovaj se treba popraviti
                navigateToMealScreen = { mealId: Int, userId: Int -> navController.navigate("${MealDestination.route}/${mealId}/${userId}")},
                navigateToCaloriesScreen = {navController.navigate("${CaloriesDestination.route}/${it}")},
                navigateToProfileScreen = { navController.navigate("${ProfileDestination.route}/${it}") }
            ) { navController.navigate(LoginDestination.route) }
        }

        composable(
            route = MealDestination.routeWithArgs,
            arguments = listOf(
                navArgument(MealDestination.mealId){
                    type = NavType.IntType
                },
                navArgument(MealDestination.userId) {
                    type = NavType.IntType
                }
            )
        ){
            backStackEntry ->
            val mealId = backStackEntry.arguments?.getInt(MealDestination.mealId) ?: 0
            val userId = backStackEntry.arguments?.getInt(MealDestination.userId) ?: 0

            if (userId == 0) {
                throw IllegalArgumentException("Missing userId")
            }
            if (mealId == 0) {
                throw IllegalArgumentException("Missing mealId")

            }
            MealScreenWithAppBar(
                navigateToBrowseMealScreen = { navController.navigate("${BrowseMealsDestination.route}/${it}") },
                navigateBack = { navController.navigateUp() },
                navigateToCaloriesScreen = {navController.navigate("${CaloriesDestination.route}/${it}")},
                navigateToProfileScreen = { navController.navigate("${ProfileDestination.route}/${it}") },
                logOut = {navController.navigate(LoginDestination.route)}
                )

        }

        composable(
            route = CaloriesDestination.routeWithArgs,
            arguments = listOf(
                navArgument(CaloriesDestination.userIdArg){
                    type = NavType.IntType
                }
            )
        ){
            CalorieScreenWithAppBar(
                navigateToBrowseMealScreen = { navController.navigate("${BrowseMealsDestination.route}/${it}") },
                navigateBack = { navController.navigateUp() },
                navigateToProfileScreen = { navController.navigate("${ProfileDestination.route}/${it}") },
                logOut = {navController.navigate(LoginDestination.route)}
            )

        }

    }
}
