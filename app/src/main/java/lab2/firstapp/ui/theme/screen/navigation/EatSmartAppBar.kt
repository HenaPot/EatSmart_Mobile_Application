package lab2.firstapp.ui.theme.screen.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import lab2.firstapp.R
import lab2.firstapp.ui.theme.SecondaryPurple
import lab2.firstapp.viewModel.AppViewModelProvider
import lab2.firstapp.viewModel.UserViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EatSmartAppBar(
    titleScreen: String,
    canNavigateBack:Boolean,
    navigateBack: () -> Unit = {},
    logOut: () -> Unit
){
    CenterAlignedTopAppBar(
        title = { Text(text = titleScreen, fontWeight = FontWeight.Light) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(onClick = navigateBack) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        },
        
        actions = {
            if(canNavigateBack) {
                TextButton(onClick = { logOut()}) {
                    Text(text = "Log out")
                }
            }
        }
    )
}

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EatSmartBottomBar(
    navigateToBrowseMealScreen: (Int) -> Unit,
    navigateToCaloriesScreen: (Int) -> Unit,
    navigateToProfileScreen:(Int) -> Unit,
    viewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    BottomAppBar(
        //backgroundColor = Color.White,
        //cutoutShape = CircleShape
        containerColor = SecondaryPurple,
        contentColor = Color.Black
    ) {
        Spacer(modifier = Modifier.width(100.dp))

        IconButton(
            onClick = {navigateToBrowseMealScreen(viewModel.userIdArg)}
        ) {
            Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
        }

        Spacer(modifier = Modifier.width(25.dp))

        IconButton(
            onClick = { navigateToCaloriesScreen(viewModel.userIdArg) }
        ) {
             Icon(painter = painterResource(id = R.drawable.calorie_counter5), contentDescription = null, modifier = Modifier.size(25.dp))
        }

        Spacer(modifier = Modifier.width(25.dp))

        IconButton(
            onClick = { navigateToProfileScreen(viewModel.userIdArg) }
        ) {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Profile")
        }
    }
}