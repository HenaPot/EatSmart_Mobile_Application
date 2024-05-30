package lab2.firstapp.ui.theme.screen

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lab2.firstapp.R
import lab2.firstapp.ui.theme.FirstApplicationTheme
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import lab2.firstapp.ui.theme.PrimaryRed
import lab2.firstapp.ui.theme.SecondaryPurple
import lab2.firstapp.ui.theme.screen.navigation.EatSmartAppBar
import lab2.firstapp.ui.theme.screen.navigation.NavigationDestination
import lab2.firstapp.ui.theme.sendNotification
import lab2.firstapp.viewModel.AppViewModelProvider
import lab2.firstapp.viewModel.LoginRegistrationViewModel


object LoginDestination: NavigationDestination {
    override val route = "login"
    override val title = "Login"
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreenWithTopBar(
    context: Context,
    navigateToRegister: () -> Unit,
    navigateToProfilePage: (Int) -> Unit
) {FirstApplicationTheme{
    Scaffold(
        // OVO OVDJE JE BOJA EKRANA
        //containerColor = PrimaryRed,
        topBar = { EatSmartAppBar(titleScreen = LoginDestination.title, canNavigateBack = false)}
    ) {
        LoginScreen(
            context = context,
            navigateToRegister = navigateToRegister,
            navigateToProfilePage = navigateToProfilePage
        )
    }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    context: Context,
    viewModel: LoginRegistrationViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToRegister: () -> Unit,
    navigateToProfilePage: (Int) -> Unit
    ) {

    val coroutineScope = rememberCoroutineScope()
    var uiState = viewModel.userUiState
    var detailsState = uiState.userDetails

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var showPassword by remember {
        mutableStateOf(false)
    }

    var checkEmail by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentWidth()
            .verticalScroll(
                rememberScrollState()
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        androidx.compose.foundation.Image(
            painter = painterResource(id = R.drawable.healthy_food_icon),
            contentDescription = "start",
            //colorFilter = ColorFilter.tint(PrimaryGreen),
            modifier = Modifier
                .padding(vertical = 10.dp)
                .size(width = 100.dp, height = 100.dp)
            )

        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.Black,
        )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 25.dp))

        TextField(
            value = email,
            onValueChange = {
                email = it;
                viewModel.updateUiState(detailsState.copy(email = it))
                            },
            enabled = true,
            label = {
                    Text(text = "email")
            },
            placeholder = {
                Text(text = "example@example.com")
            },
            isError = !checkEmail,
            modifier = Modifier.background(SecondaryPurple),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.size(height = 25.dp, width = 0.dp))

        TextField(
            value = password,
            onValueChange = {
                password = it;
                viewModel.updateUiState(detailsState.copy(password = it))
                            },
            label = {Text(text = "password")},
            isError = false,
            enabled = true,
            visualTransformation = if(showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },

            trailingIcon = {
                Icon(
                    painter = if(showPassword) {
                        painterResource(id = R.drawable.icons8_show_50)
                    } else {
                        painterResource(id = R.drawable.icons8_hide_50)
                    },
                    contentDescription = "",
                    modifier = Modifier.clickable(onClick = {showPassword = !showPassword})
                    )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 0.dp)
        )

        TextButton(
            onClick = {
                      /*sendNotification(title = "It sucks to be you lmao",
                          notificationBodyText = "Notification body test",
                          count = 1,
                          context = context)*/
                      navigateToRegister()
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Do not have an account?",
                fontWeight = FontWeight.Light,

            )
        }
        Spacer(modifier = Modifier.size(width = 0.dp, height = 25.dp))


        Button(onClick = {
            //checkEmail = checkEmail(email);
            coroutineScope.launch {
                //checkEmail = viewModel.checkEmail();
                Log.d("pre login", viewModel.userUiState.toString())
                // LOGIN DOES NOT WORK AND NEVER SHOWS UP IN LOGCAT
                // pre login shows in logcat and logs user's input
                if(viewModel.login()){
                    Log.d("login", viewModel.userUiState.toString())
                    navigateToProfilePage(viewModel.userUiState.userDetails.id)
                }
            }
        }) {
            Text(
                text = "Login",
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 0.dp)
            )
        }
    }
}

fun checkEmail(email: String): Boolean{
    return EMAIL_ADDRESS.matcher(email).matches()
}

@Preview
@Composable
fun LoginScreenPreview() {
    FirstApplicationTheme {
        //LoginScreen()
    }
}