package lab2.firstapp.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lab2.firstapp.R
import lab2.firstapp.ui.theme.TertiaryColor

@Composable
fun RegistrationScreen(){
    var name by remember {
        mutableStateOf("")
    }
    var surname by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var showPassword by remember {
        mutableStateOf(true)
    }
    var repeatPassword by remember {
        mutableStateOf("")
    }
    var showRepeatPassword by remember {
        mutableStateOf(true)
    }
    var checkEmail by remember {
        mutableStateOf(true)
    }
    var checkPassword by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentWidth()
            .padding(vertical = 50.dp, horizontal = 0.dp)
            .verticalScroll(
                rememberScrollState()
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(painter = painterResource(id = R.drawable.healthy_food_icon) , contentDescription = "")

        Spacer(modifier = Modifier.size(width = 0.dp, height = 30.dp))

        Text(
            text = stringResource(id = R.string.registration),
            fontSize = 30.sp, // SP KAD IMAS FONTOVE
            fontFamily = FontFamily.Cursive,
            color = Color.Black,
        )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 30.dp))

        TextField(
            value = name,
            onValueChange = {name = it},
            isError = false,
            enabled = true,
            label = { Text(text = "name")},
            placeholder = { Text(text = "Jane")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 30.dp))

        TextField(
            value = surname,
            onValueChange = {surname = it},
            isError = false,
            enabled = true,
            label = { Text(text = "surname")},
            placeholder = { Text(text = "Doe")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 30.dp))

        TextField(
            value = email,
            onValueChange = {email = it},
            isError = !checkEmail,
            enabled = true,
            label = { Text(text = "email")},
            placeholder = { Text(text = "example@example.com")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 30.dp))

        TextField(
            value = password,
            onValueChange = {password = it},
            isError = false,
            enabled = true,
            label = { Text(text = "password")},
            placeholder = { Text(text = "12345678")},
            visualTransformation = if(!showPassword){
                VisualTransformation.None
            } else{
                  PasswordVisualTransformation()
                  },
            trailingIcon = {
                Icon(
                    painter = if(showPassword){
                                    painterResource(id = R.drawable.icons8_show_50)
                              } else {
                                    painterResource(id = R.drawable.icons8_hide_50)
                              },
                    contentDescription = "",
                    modifier = Modifier.clickable(onClick = {showPassword = !showPassword})
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next)
            )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 30.dp))

        TextField(
            value = repeatPassword,
            onValueChange = {repeatPassword = it},
            //isError = password!=repeatPassword,
            isError = !checkPassword,
            enabled = true,
            label = { Text(text = "repeat password")},
            //placeholder = { Text(text = "12345678")},
            visualTransformation = if(!showRepeatPassword){
                VisualTransformation.None
            } else{
                PasswordVisualTransformation()
            },
            trailingIcon = {
                Icon(
                    painter = if(showRepeatPassword){
                        painterResource(id = R.drawable.icons8_show_50)
                    } else {
                        painterResource(id = R.drawable.icons8_hide_50)
                    },
                    contentDescription = "",
                    modifier = Modifier.clickable(onClick = {showRepeatPassword = !showRepeatPassword})
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done)
            )

            TextButton(
                onClick = {/*TODO*/},
                modifier = Modifier.align(Alignment.End)
                ) {
                Text(
                    text = "Have an account?",
                    color = TertiaryColor
                )
            }

            Spacer(modifier = Modifier.size(width = 0.dp, height = 30.dp))

            Button(onClick = {
                checkEmail = checkEmail(email);
                checkPassword = password == repeatPassword;
            }) {
                Text(text = "Register")
            }

    }
}

@Composable
@Preview(showBackground = true)
fun RegistrationPreview(){
    RegistrationScreen()
}