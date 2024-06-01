package lab2.firstapp.ui.theme.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import lab2.firstapp.R
import lab2.firstapp.model.models.Meal
import lab2.firstapp.ui.theme.PrimaryRed
import lab2.firstapp.ui.theme.SecondaryPurple
import lab2.firstapp.ui.theme.screen.navigation.EatSmartAppBar
import lab2.firstapp.ui.theme.screen.navigation.EatSmartBottomBar
import lab2.firstapp.ui.theme.screen.navigation.NavigationDestination
import lab2.firstapp.viewModel.AllMealsViewModel
import lab2.firstapp.viewModel.AppViewModelProvider
import lab2.firstapp.viewModel.UserMealHistoryViewModel
import lab2.firstapp.viewModel.UserViewModel

object BrowseMealsDestination: NavigationDestination {
    override val route = "Browse Meals"
    override val title = "Browse Meals"
    const val userIdArg = "userID"
    val routeWithArgs = "${BrowseMealsDestination.route}/{$userIdArg}"
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BrowseMealsScreenWithAppBar(
    navigateToMealScreen: (Int, Int) -> Unit,
    navigateBack: () -> Unit,
    navigateToBrowseMealScreen: (Int) -> Unit,
    navigateToCaloriesScreen: (Int) -> Unit,
    navigateToProfileScreen: (Int) -> Unit,
    logOut: () -> Unit,

    ){
    Scaffold(
        topBar = { EatSmartAppBar(titleScreen = BrowseMealsDestination.title, canNavigateBack = true, navigateBack = navigateBack, logOut = logOut) },
        bottomBar = { EatSmartBottomBar(navigateToBrowseMealScreen = navigateToBrowseMealScreen, navigateToCaloriesScreen = navigateToCaloriesScreen, navigateToProfileScreen = navigateToProfileScreen)}
    ) {
        BrowseMealsScreen(navigateToMealScreen = navigateToMealScreen)
    }
}

@Composable
fun BrowseMealsScreen(
    viewModel: AllMealsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToMealScreen: (Int, Int) -> Unit,
){
    val allMealsUiState by viewModel.allMealsUiState.collectAsState()
    Log.d("all meals", allMealsUiState.mealList.toString())

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentWidth()
    ) {
        Text(
            text = "Healthy Meal Ideas",
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            color = PrimaryRed,
            modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
        )
        //}
        /*LazyRow {
            items(AllMeals.meals) { meal ->
                MealCard(meal = meal)
            }
        }
        Divider()*/

        LazyColumn() {
            items(allMealsUiState.mealList) { meal ->
                BigMealCard(meal = meal, navigateToMealScreen = navigateToMealScreen, viewModel = viewModel)
            }
        }
        // fix the fact that you cant see button of last card
        Spacer(modifier = Modifier.height(60.dp))
    }
}

@Composable
fun BigMealCard(meal: Meal, navigateToMealScreen: (Int, Int) -> Unit, viewModel: AllMealsViewModel) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = PrimaryRed
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, start = 5.dp, end = 10.dp)
        ) {
            Text(text = meal.name, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 20.dp))

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(id = R.drawable.meal0),
                contentDescription = meal.name,
                modifier = Modifier/*.size(115.dp)*/
                    .border(2.dp, Color.White)
                    .padding(top = 5.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Calories: ${meal.calories}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(15.dp))
            //ArrayToText(strings = meal.ingredientArray, nameString = "Ingredients")
            Text(text = "Ingredients", color = Color.White)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = meal.ingredientArray, color = Color.White, modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp))

            Spacer(modifier = Modifier.height(15.dp))
            //ArrayToText(strings = meal.directionsArray, nameString = "Directions")
            Text(text = "Directions", color = Color.White)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = meal.directionString.dropLast(450) + "...",
                color = Color.White,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { navigateToMealScreen(meal.id, viewModel.userId) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = SecondaryPurple,
                    contentColor = PrimaryRed
                )
            ) {
                Text(text = "Open Recipe")
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

/*
@Composable
fun ArrayToText(strings: Array<String>, nameString: String, color: Color = Color.White, fullMealDescription: Boolean = false) {
    var arrayMeals: String = "$nameString: "
    strings.map {
        if(arrayMeals == "$nameString: ") {
            arrayMeals = "$arrayMeals $it"
        } else if(nameString == "Directions") {
            arrayMeals = "$arrayMeals $it"
        }
        else{
            arrayMeals = "$arrayMeals, $it"
        }

    }
    if(nameString == "Directions" && !fullMealDescription) {
        arrayMeals = arrayMeals.dropLast(450)
        arrayMeals += "..."
    }
    Text(text = arrayMeals, modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp), color = color)
}
*/