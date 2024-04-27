package lab2.firstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import lab2.firstapp.ui.theme.FirstApplicationTheme
import lab2.firstapp.ui.theme.SecondaryPurple
import lab2.firstapp.ui.theme.screen.LoginScreen
import lab2.firstapp.ui.theme.screen.MealScreen
import lab2.firstapp.ui.theme.screen.PreferenceScreen
import lab2.firstapp.ui.theme.screen.ProfileScreen
import lab2.firstapp.ui.theme.screen.RegistrationScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = SecondaryPurple
                ) {
                    //LoginScreen(this)
                    //RegistrationScreen()
                    ProfileScreen()
                    //PreferenceScreen()
                    //MealScreen()
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