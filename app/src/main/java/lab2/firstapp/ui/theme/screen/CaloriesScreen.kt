package lab2.firstapp.ui.theme.screen

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lab2.firstapp.R
import lab2.firstapp.ui.theme.PrimaryRed

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CalorieScreen(){
    var calories by remember {
        mutableStateOf("0")
    }
    var expandedAddMeal by remember {
        mutableStateOf(false)
    }
    var enableTextField by remember {
        mutableStateOf(false)
    }
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .wrapContentWidth()
            .fillMaxSize()
    ) {

        Text(
            text = "Stay on track!",
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold,
            color = PrimaryRed,
            modifier = Modifier.padding(
                20.dp
            )
        )

        Text(text = "Calorie Counter", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = PrimaryRed, modifier = Modifier.padding(top = 20.dp))
        Spacer(modifier = Modifier.height(15.dp))

        Box() {
            TextField(
                value = calories,
                onValueChange = {calories = it; enableTextField = true},
                trailingIcon = {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = null,
                        modifier = Modifier.clickable(onClick = {expandedAddMeal = true})
                    )
                },
                enabled = enableTextField,
                isError = false,
                readOnly = false,
                label = { Text(text = "Calories")},
                placeholder = { Text(text = calories)},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
            )

            DropdownMenu(
                expanded = expandedAddMeal,
                onDismissRequest = { expandedAddMeal = false; enableTextField=false },
                modifier = Modifier.width(280.dp)
            )
            {
                DropdownMenuItem(
                    text = { Text(text = "Add meal")},
                    onClick = {/*NAVIGATE TO MEALS SCREEN*/}
                )

                DropdownMenuItem(
                    text = { Text(text = "Add custom calories")},
                    onClick = {enableTextField = true}
                )
            }
        }

        Spacer(modifier = Modifier.height(35.dp))
        Divider()
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Calories History", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = PrimaryRed, modifier = Modifier.padding(top = 20.dp))
        LazyColumn {
            // ??? kako implementirati historiju
        }

    }
}