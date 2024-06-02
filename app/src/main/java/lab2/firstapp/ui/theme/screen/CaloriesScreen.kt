package lab2.firstapp.ui.theme.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import lab2.firstapp.R
import lab2.firstapp.model.models.Meal
import lab2.firstapp.ui.theme.PrimaryRed
import lab2.firstapp.ui.theme.SecondaryPurple
import lab2.firstapp.ui.theme.screen.navigation.EatSmartAppBar
import lab2.firstapp.ui.theme.screen.navigation.EatSmartBottomBar
import lab2.firstapp.ui.theme.screen.navigation.NavigationDestination
import lab2.firstapp.viewModel.AppViewModelProvider
import lab2.firstapp.viewModel.UserMealHistoryViewModel
import java.text.SimpleDateFormat
import java.util.Calendar

object CaloriesDestination: NavigationDestination {
    override val route = "calories"
    override val title = "Calories"
    const val userIdArg = "userID"
    val routeWithArgs = "$route/{$userIdArg}"
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CalorieScreenWithAppBar(
    navigateToBrowseMealScreen: (Int) -> Unit,
    navigateBack: () -> Unit,
    navigateToCaloriesScreen: (Int) -> Unit = {},
    navigateToProfileScreen: (Int) -> Unit,
    logOut: () -> Unit
) {
    Scaffold(
        topBar = { EatSmartAppBar(titleScreen = CaloriesDestination.title, canNavigateBack = true, navigateBack = navigateBack, logOut = logOut)},
        bottomBar = { EatSmartBottomBar(navigateToBrowseMealScreen = navigateToBrowseMealScreen, navigateToCaloriesScreen = navigateToCaloriesScreen, navigateToProfileScreen = navigateToProfileScreen) }
    ) {

        CalorieScreen(navigateToBrowseMealScreen = navigateToBrowseMealScreen)
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CalorieScreen(
    //when you want to change user you must also change userId in the viewModel, it is hardcoded
    navigateToBrowseMealScreen: (Int) -> Unit,
    date: String = getTodayDateString(),
    viewModel: UserMealHistoryViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    val mealHistoryUiState by viewModel.userMealHistoryUiState.collectAsState()
    val totalCalories = viewModel.totalCalories.collectAsState()

    LaunchedEffect(viewModel.userIdArg, date) {
        viewModel.getUsersCaloriesOnDate(viewModel.userIdArg, date)
        viewModel.getUsersCaloriesOnDate(viewModel.userIdArg, date)
    }

    var expandedAddMeal by remember {
        mutableStateOf(false)
    }

    val datePickerState = rememberDatePickerState()
    var openCalendar by remember {
        mutableStateOf(false)
    }
    var mutableDate by remember {
        mutableStateOf(date)
    }

    val coroutineScope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .wrapContentWidth()
            .fillMaxSize()
            .padding(top = 30.dp, bottom = 90.dp)
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        /*Text(
            text = "Stay on track!",
            fontSize = 20.sp,
            //fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold,
            color = PrimaryRed,
            modifier = Modifier.padding(
                25.dp
            )
        )*/

        //Divider()
        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "Meal Calorie Counter", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = PrimaryRed, modifier = Modifier.padding(top = 20.dp))
        Spacer(modifier = Modifier.height(15.dp))

        Box() {
            TextField(
                value = totalCalories.value ?: "0",
                onValueChange = {
                    //totalCalories = it;
                    /* THESE ARE NOT NEEDED BECAUSE HERE WE WORK WITH STATEFLOWS DIRECTLY AND THEY REFLECT CHANGES FROM DB TO UI
                    userViewModel.updateUiState(userDetailsState.copy(calories = it.toInt()));
                    coroutineScope.launch {
                        userViewModel.updateUser()
                    }*/
                                },
                colors = TextFieldDefaults.colors(
                    disabledTextColor = Color.Black,
                    disabledLabelColor = Color.Black,
                    disabledTrailingIconColor = Color.Black
                ),
                trailingIcon = {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = null,
                        modifier = Modifier.clickable(onClick = {expandedAddMeal = true})
                    )
                },
                enabled = false,
                isError = false,
                readOnly = true,
                label = { Text(text = "Calories")},
                placeholder = { Text(text = "calories")},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
            )

            DropdownMenu(
                expanded = expandedAddMeal,
                onDismissRequest = { expandedAddMeal = false;},
                modifier = Modifier.width(280.dp)
            )
            {
                DropdownMenuItem(
                    text = { Text(text = "Add meal")},
                    onClick = {/*NAVIGATE TO MEALS SCREEN*/
                        navigateToBrowseMealScreen(viewModel.userIdArg)
                    }
                )

                /*DropdownMenuItem(
                    text = { Text(text = "Custom adjust calories")},
                    onClick = {
                    }
                )*/
            }
        }

        Spacer(modifier = Modifier.height(60.dp))
        Divider()
        Spacer(modifier = Modifier.height(30.dp))

        TextButton(onClick = {openCalendar = true}) {
            Text(text = "Choose Date: $mutableDate", fontWeight = FontWeight.ExtraBold)
        }

        if(openCalendar) {
            DatePickerDialog(
                onDismissRequest = {openCalendar = false},
                confirmButton = {
                    Button(
                        onClick = { openCalendar = false;

                            val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
                            val dateString: String = formatter.format(datePickerState.selectedDateMillis)
                            mutableDate = dateString;

                            //viewModel
                            coroutineScope.launch {
                                viewModel.getUserHistory(viewModel.userIdArg, mutableDate)
                                viewModel.getUsersCaloriesOnDate(viewModel.userIdArg, mutableDate)
                            }

                        }
                    )
                    {
                        Text(text = "Confirm Birthdate")
                    }
                },
            )
            {
                DatePicker(
                    state = datePickerState
                )
            }
        }

        //Spacer(modifier = Modifier.height(5.dp))

        Text(text = "Meal History", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = PrimaryRed, modifier = Modifier.padding(top = 20.dp, bottom = 10.dp))

        if(mealHistoryUiState.mealHistoryList.isEmpty()) {
            Text(text = "No meals added today.", color = PrimaryRed)
        } else {
            LazyColumn() {
                items(mealHistoryUiState.mealHistoryList) { meal ->
                    MealCard(meal = meal, viewModel = viewModel)
                }
            }
        }

        Spacer(modifier = Modifier.height(60.dp))
    }
}


@Composable
fun MealCard(meal: Meal, viewModel: UserMealHistoryViewModel){
    val coroutineScope = rememberCoroutineScope()
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(5.dp)
            .width(350.dp)
            .height(120.dp),
        colors = CardDefaults.cardColors(
            containerColor = PrimaryRed
        )
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, start = 5.dp, end = 10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.meal0),
                contentDescription = meal.name,
                modifier = Modifier
                    .size(110.dp)
                    .padding(top = 3.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column{
                Text(text = meal.name, fontSize = 15.sp)
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .wrapContentHeight()
                        .wrapContentWidth()
                ){
                    Text(text = "Calories: ${meal.calories}", fontSize = 13.sp)
                    Spacer(modifier = Modifier.width(20.dp))
                    Button(
                        onClick = {
                             //THESE ARE NOT NECESSARY AS

                            coroutineScope.launch {
                                viewModel.deleteMealHistory(meal.id)

                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SecondaryPurple,
                            contentColor = PrimaryRed,
                        )
                    ) {
                        Text(text = "Delete", color = PrimaryRed)
                    }
                }
            }
        }
    }
}

fun getTodayDateString(): String {
    val time = Calendar.getInstance().time
    val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    return formatter.format(time)
}
