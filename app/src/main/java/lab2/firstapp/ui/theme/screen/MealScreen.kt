package lab2.firstapp.ui.theme.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import lab2.firstapp.R
import lab2.firstapp.ui.theme.PrimaryRed
import lab2.firstapp.ui.theme.SecondaryPurple
import lab2.firstapp.ui.theme.screen.navigation.EatSmartAppBar
import lab2.firstapp.ui.theme.screen.navigation.EatSmartBottomBar
import lab2.firstapp.ui.theme.screen.navigation.NavigationDestination
import lab2.firstapp.viewModel.AppViewModelProvider
import lab2.firstapp.viewModel.MealViewModel
import lab2.firstapp.viewModel.UserMealHistoryViewModel

object MealDestination: NavigationDestination {
    override val route = "meal"
    override val title = "Meal"
    const val mealId = "mealId"
    const val userId = "userID"
    val routeWithArgs = "$route/{$mealId}/{$userId}"
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MealScreenWithAppBar(
    navigateBack : () -> Unit,
    navigateToBrowseMealScreen: (Int) -> Unit,
    navigateToCaloriesScreen: (Int) -> Unit,
    navigateToProfileScreen: (Int) -> Unit,
    logOut: () -> Unit
){
    Scaffold(
        topBar = { EatSmartAppBar(titleScreen = MealDestination.title, canNavigateBack = true, navigateBack = navigateBack, logOut = logOut)},
        bottomBar = { EatSmartBottomBar(navigateToBrowseMealScreen = navigateToBrowseMealScreen, navigateToCaloriesScreen = navigateToCaloriesScreen, navigateToProfileScreen = navigateToProfileScreen)}
    ) {
        MealScreen(navigateToCalorieScreen = navigateToCaloriesScreen)
    }
}

@Composable
fun MealScreen(
    viewModel: MealViewModel = viewModel(factory = AppViewModelProvider.Factory),
    historyViewModel: UserMealHistoryViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToCalorieScreen: (Int) -> Unit
){
    val coroutineScope = rememberCoroutineScope()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .wrapContentWidth()
            .fillMaxSize()
            .padding(top = 5.dp, bottom = 5.dp, start = 5.dp, end = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Text(text = viewModel.mealUiState.mealDetails.name, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = PrimaryRed, modifier = Modifier.padding(top = 20.dp))

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            //picture will have to be adjusted to draw from DB
            painter = painterResource(id = viewModel.mealUiState.mealDetails.mealImage),
            contentDescription = viewModel.mealUiState.mealDetails.name,
            modifier = Modifier/*.size(115.dp)*/
                .border(2.dp, PrimaryRed)
                .padding(top = 5.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Calories: ${viewModel.mealUiState.mealDetails.calories}", color = PrimaryRed, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(15.dp))
        //ArrayToText(strings = meal.ingredientArray, nameString = "Ingredients", PrimaryRed, true)
        Text(text = "Ingredients", color = PrimaryRed)
        Text(text = viewModel.mealUiState.mealDetails.ingredientArray, color = PrimaryRed, modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp))

        Spacer(modifier = Modifier.height(15.dp))
        //ArrayToText(strings = meal.directionsArray, nameString = "Directions", PrimaryRed, true)
        Text(text = "Directions", color = PrimaryRed)
        Text(text = viewModel.mealUiState.mealDetails.directionString, color = PrimaryRed, modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp))

        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                //SEND CALORIES TO THE CALORIE SCREEN AS WELL
                //viewModel.mealUiState.mealDetails.calories
                coroutineScope.launch {
                    historyViewModel.addMealToHistory(userId = historyViewModel.userIdArg, mealId = viewModel.mealUiState.mealDetails.id, date =  getTodayDateString())
                }

                navigateToCalorieScreen(historyViewModel.userIdArg)
                /*coroutineScope.launch {
                    historyViewModel.updateMealHistory(8, 2, viewModel.mealUiState.mealDetails.id, "2024-05-20")
                }*/
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = SecondaryPurple,
                contentColor = PrimaryRed,
            ),
            border = BorderStroke(2.dp, PrimaryRed)
        ) {
            Text(text = "Add Meal")
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}