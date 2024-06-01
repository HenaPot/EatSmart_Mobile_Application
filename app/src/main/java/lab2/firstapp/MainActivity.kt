package lab2.firstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import lab2.firstapp.model.AllMeals
import lab2.firstapp.model.Meal
import lab2.firstapp.ui.theme.FirstApplicationTheme
import lab2.firstapp.ui.theme.PrimaryRed
import lab2.firstapp.ui.theme.SecondaryPurple
import lab2.firstapp.ui.theme.screen.BrowseMealsScreen
import lab2.firstapp.ui.theme.screen.CalorieScreen
import lab2.firstapp.ui.theme.screen.LoginScreen
import lab2.firstapp.ui.theme.screen.MealScreen
import lab2.firstapp.ui.theme.screen.PreferenceScreen
import lab2.firstapp.ui.theme.screen.ProfileScreen
import lab2.firstapp.ui.theme.screen.RegistrationScreen
import lab2.firstapp.ui.theme.screen.navigation.EatSmartNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = SecondaryPurple
                ) {
                   // LoginScreen(this, navigateToRegister= {}, navigateToProfilePage={})
                    //RegistrationScreen()
                    //ProfileScreen()
                    //PreferenceScreen()
                    //BrowseMealsScreen()
                    //MealScreen()
                    //CalorieScreen()
                    EatSmartNavHost(navController = rememberNavController())
                }

            }
        }
    }
}


/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstApplicationTheme {
        LoginScreen()
    }
}*/