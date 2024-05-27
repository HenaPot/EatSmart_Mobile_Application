package lab2.firstapp.ui.theme.screen.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import lab2.firstapp.ui.theme.SecondaryPurple


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EatSmartAppBar(
    titleScreen: String,
    canNavigateBack:Boolean,
    navigateBack: () -> Unit = {}
){
    CenterAlignedTopAppBar(
        title = { Text(text = titleScreen) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = SecondaryPurple),
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(onClick = navigateBack) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        }
    )
}