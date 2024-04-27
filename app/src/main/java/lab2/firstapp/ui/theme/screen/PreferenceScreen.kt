package lab2.firstapp.ui.theme.screen

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lab2.firstapp.model.ActivityLevel
import lab2.firstapp.model.DietaryPlan
import lab2.firstapp.model.FitnessPlan
import lab2.firstapp.ui.theme.PrimaryRed

@Composable
fun PreferenceScreen(){
    var expandedDietaryPlan by remember {
        mutableStateOf(false)
    }
    var dropDownDietaryPlan by remember {
        mutableStateOf("carnivore")
    }
    var expandedFitnessPlan by remember {
        mutableStateOf(false)
    }
    var dropDownFitnessPlan by remember {
        mutableStateOf("maintain weight")
    }
    var weight by remember {
        mutableStateOf("")
    }
    var expandedActivityLevel by remember {
        mutableStateOf(false)
    }
    var dropDownActivityLevel by remember {
        mutableStateOf("lightly active")
    }
    var height by remember {
        mutableStateOf("")
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentWidth()
    ) {
        Text(
            text = "Preferences",
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold,
            color = PrimaryRed
        )

        Spacer(modifier = Modifier.size(height = 55.dp, width = 0.dp))
        
        TextField(
            value = weight,
            onValueChange = {weight = it},
            isError = false,
            enabled = true,
            colors = TextFieldDefaults.colors(
                focusedPlaceholderColor = Color.Gray,
                unfocusedPlaceholderColor = Color.Gray
            ),
            label = { Text(text = "Weight in kg")},
            placeholder = { Text(text = "55")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )
        
        Spacer(modifier = Modifier.size(height = 15.dp, width = 0.dp))

        TextField(
            value = height,
            onValueChange = {height = it},
            isError = false,
            enabled = true,
            colors = TextFieldDefaults.colors(
                focusedPlaceholderColor = Color.Gray,
                unfocusedPlaceholderColor = Color.Gray
            ),
            label = { Text(text = "Height in m")},
            placeholder = { Text(text = "1.65")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.size(height = 15.dp, width = 0.dp))
        
        Box() {
            TextField(
                value = dropDownDietaryPlan,
                onValueChange = {dropDownDietaryPlan = it},
                placeholder = { Text(text = "carnivore") },
                label = { Text(text = "Dietary Plan") },
                isError = false,
                enabled = false,
                colors = TextFieldDefaults.colors(
                    disabledTextColor = Color.Black,
                    disabledLabelColor = Color.Black,
                    disabledTrailingIconColor = Color.Black
                ),
                readOnly = true,
                trailingIcon = {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = null,
                        modifier = Modifier.clickable {expandedDietaryPlan = true})
                }
            )

            DropdownMenu(
                expanded = expandedDietaryPlan,
                onDismissRequest = {expandedDietaryPlan = false},
                modifier = Modifier.width(280.dp)
            ) {
                -> DietaryPlan.entries.map {
                DropdownMenuItem(
                    text = { Text(text = it.dietaryPlan) },
                    onClick = {
                        dropDownDietaryPlan = it.dietaryPlan
                        expandedDietaryPlan = false
                    },
                    modifier = Modifier.fillMaxSize()
                )
                }
            }
        }

        Spacer(modifier = Modifier.size(height = 15.dp, width = 0.dp))

        Box() {
            TextField(
                value = dropDownFitnessPlan,
                onValueChange = {dropDownFitnessPlan = it},
                label = { Text(text = "Fitness Plan")},
                isError = false,
                readOnly = true,
                enabled = false,
                colors = TextFieldDefaults.colors(
                    disabledTextColor = Color.Black,
                    disabledLabelColor = Color.Black,
                    disabledTrailingIconColor = Color.Black
                ),
                trailingIcon = {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            expandedFitnessPlan = true
                        }
                    )
                }
            )
            DropdownMenu(
                expanded = expandedFitnessPlan,
                onDismissRequest = { expandedFitnessPlan = false },
                modifier = Modifier.width(280.dp)
            )
            {
                -> FitnessPlan.entries.map {
                    DropdownMenuItem(
                        text = { Text(text = it.fitnessPlan)},
                        onClick = { dropDownFitnessPlan = it.fitnessPlan; expandedFitnessPlan = false},
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(height = 15.dp, width = 0.dp))

        Box {
            TextField(
                value = dropDownActivityLevel,
                onValueChange = {dropDownActivityLevel = it},
                readOnly = true,
                isError = false,
                enabled = false,
                colors = TextFieldDefaults.colors(
                    disabledTextColor = Color.Black,
                    disabledLabelColor = Color.Black,
                    disabledTrailingIconColor = Color.Black
                ),
                label = { Text(text = "Activity Level")},
                placeholder = { Text(text = "lightly active")},
                trailingIcon = {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            expandedActivityLevel = true
                        }
                    )
                }
            )
            DropdownMenu(
                expanded = expandedActivityLevel,
                onDismissRequest = { expandedActivityLevel = false},
                modifier = Modifier.width(280.dp)
            )
            {
                -> ActivityLevel.entries.map {
                    DropdownMenuItem(
                        text = { Text(text = it.activityLevel)},
                        onClick = {expandedActivityLevel = false; dropDownActivityLevel = it.activityLevel},
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }


    }

}


@Composable
@Preview(showBackground = true)
fun PreferenceScreenPreview(){
    PreferenceScreen()
}