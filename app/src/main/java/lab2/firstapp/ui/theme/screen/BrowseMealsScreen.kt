package lab2.firstapp.ui.theme.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import lab2.firstapp.model.AllMeals
import lab2.firstapp.model.models.Meal
import lab2.firstapp.ui.theme.PrimaryRed
import lab2.firstapp.ui.theme.SecondaryPurple
import lab2.firstapp.viewModel.AllMealsViewModel
import lab2.firstapp.viewModel.AppViewModelProvider

@Composable
fun BrowseMealsScreen(
    viewModel: AllMealsViewModel = viewModel(factory = AppViewModelProvider.Factory)
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
                BigMealCard(meal = meal)
            }
        }
    }
}

@Composable
fun BigMealCard(meal: Meal) {
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
                onClick = { /*TODO*/ },
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