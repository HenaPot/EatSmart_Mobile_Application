package lab2.firstapp.ui.theme.screen

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lab2.firstapp.model.AllMeals
import lab2.firstapp.model.Meal
import lab2.firstapp.ui.theme.PrimaryRed

@Composable
fun MealScreen(){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Healthy Meal Ideas",
            fontFamily = FontFamily.Cursive,
            fontSize = 30.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)
        )
    //}
        LazyRow {
            items(AllMeals.meals) {
                meal -> MealCard(meal = meal)
            }
        }
        // ovdje crasha ako stavim lazy column a treba mi lazy column
        LazyRow {
            items(AllMeals.meals) {
                meal -> BigMealCard(meal = meal)
            }
        }
    }
}

@Composable
fun MealCard(meal: Meal){
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(5.dp)
            .width(350.dp)
            .height(120.dp)
            .clickable(onClick = {/*NAPRAV TAKO DA KAD KLIKNES NA KARTICU OTVORI SE TAJ RECEPT DOLE U VELIKOJ KARTICI*/ }),
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
                painter = painterResource(id = meal.image),
                contentDescription = meal.name,
                modifier = Modifier
                    .size(110.dp)
                    .padding(top = 3.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column{
                Text(text = meal.name, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Calories: ${meal.calories}", fontSize = 16.sp)
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
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, start = 5.dp, end = 10.dp)
        ) {
            Image(
                painter = painterResource(id = meal.image),
                contentDescription = meal.name,
                modifier = Modifier/*.size(115.dp)*/.padding(top = 5.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column(

            ) {
                Text(text = meal.name, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Calories: ${meal.calories}", fontSize = 16.sp)
                TextList(strings = meal.ingredientArray)
                Spacer(modifier = Modifier.height(10.dp))
                TextList(strings = meal.directionsArray)
            }

        }
    }
}

@Composable
fun TextList(strings: Array<String>) {
    LazyColumn {
        items(strings) { string ->
            Text(text = string)
        }
    }
}