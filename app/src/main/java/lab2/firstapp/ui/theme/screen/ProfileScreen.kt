package lab2.firstapp.ui.theme.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import lab2.firstapp.R
import lab2.firstapp.model.Gender
import lab2.firstapp.ui.theme.PrimaryRed
import lab2.firstapp.ui.theme.screen.navigation.EatSmartAppBar
import lab2.firstapp.ui.theme.screen.navigation.NavigationDestination
import lab2.firstapp.viewModel.AppViewModelProvider
import lab2.firstapp.viewModel.UserViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate

object ProfileDestination: NavigationDestination {
    override val route = "profile"
    override val title = "Profile"
    const val userIdArg = "userID"
    val routeWithArgs = "$route/{$userIdArg}"
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreenWithTopBar(
    navigateBack: () -> Unit,
    navigateToPreferences: (Int) -> Unit
) {
    Scaffold(
        topBar = { EatSmartAppBar(titleScreen = ProfileDestination.title, canNavigateBack = true, navigateBack = navigateBack)}
    ) {
        ProfileScreen(navigateToPreferences = navigateToPreferences)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToPreferences: (Int) -> Unit
){
    var uiState = viewModel.userUiState
    var detailsState = uiState.userDetails
    val coroutineScope = rememberCoroutineScope()

    var name = "Hena"
    var surname = "Potogija"
    var email = "potogijahena@gmail.com"
    var creatorEmail = "hena.potogija@stu.ibu.edu.ba"
    var creatorPhoneNumber = 340893284
    var dropDownGender by remember {
        mutableStateOf("female")
    }
    var expandedGender by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    //LocalTextInputService provides null
    val datePickerState = rememberDatePickerState()
    var openCalendar by remember {
        mutableStateOf(false)
    }
    var showDate by remember {
        mutableStateOf(false)
    }
    var DOB by remember {
        mutableStateOf("2022-02-23")
    }

    Log.d("profile", viewModel.userUiState.toString() )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentWidth()
            .padding(horizontal = 0.dp, vertical = 30.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        ProfileImage()

        Spacer(modifier = Modifier.size(width = 0.dp, height = 30.dp))

        Row {
            TextField(
                value = viewModel.userUiState.userDetails.name,
                onValueChange = {},
                label = { Text(text = "name") },
                isError = false,
                readOnly = true,
                modifier = Modifier.width(135.dp)
            )
            Spacer(modifier = Modifier.size(width = 5.dp, height = 0.dp))

            TextField(
                value = viewModel.userUiState.userDetails.surname,
                onValueChange = {},
                label = { Text(text = "surname") },
                isError = false,
                readOnly = true,
                modifier = Modifier.width(135.dp)
            )
        }
        Spacer(modifier = Modifier.size(width = 0.dp, height = 15.dp))

        TextField(
            value = viewModel.userUiState.userDetails.email,
            onValueChange = {},
            label = { Text(text = "email") },
            isError = false,
            readOnly = true
        )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 15.dp))

        Box {
            TextField(
                value = viewModel.userUiState.userDetails.gender.gender,
                onValueChange = { dropDownGender = it },
                isError = false,
                enabled = false,
                readOnly = true,
                colors = TextFieldDefaults.colors(
                    disabledTextColor = Color.Black,
                    disabledLabelColor = Color.Black,
                    disabledTrailingIconColor = Color.Black
                ),
                placeholder = { Text(text = "female") },
                label = { Text(text = "Gender") },
                trailingIcon = {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = null,
                        //painter = painterResource(id = R.drawable.android_menu_512),
                        //contentDescription = "",
                        modifier = Modifier
                            //.size(25.dp)
                            .clickable(onClick = { expandedGender = true })
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    //keyboardType = KeyboardType.None
                )

            )

            DropdownMenu(
                expanded = expandedGender,
                onDismissRequest = { expandedGender = false },
                modifier = Modifier.width(280.dp)
            )
            {
                ->
                Gender.entries.map {
                    DropdownMenuItem(
                        text = { Text(text = it.gender) },
                        onClick = {
                            dropDownGender = it.gender;
                            expandedGender = false;
                            viewModel.updateUiState(detailsState.copy(gender = it));
                            coroutineScope.launch {
                                viewModel.updateUser()
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(height = 15.dp, width = 0.dp))


        TextField(
            value = 
                //formatDateFromString(DOB)
                //val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
                 //"${LocalDate.parse(DOB).year.toString()} ${LocalDate.parse(DOB).month.toString()} ${LocalDate.parse(DOB).dayOfMonth.toString()}"
                viewModel.userUiState.userDetails.dateOfBirth
            ,
            onValueChange = {},
            readOnly = true,
            isError = false,
            label = { Text(text = "Date of Birth")},
            placeholder = { },
            trailingIcon = {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = null,
                    modifier = Modifier.clickable {openCalendar=true}
                )
            }

        )

        if(openCalendar) {
            DatePickerDialog(
                onDismissRequest = {openCalendar = false},
                confirmButton = {
                    Button(
                        onClick = {showDate = true; openCalendar = false

                            val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
                            val dateString: String = formatter.format(datePickerState.selectedDateMillis)
                            DOB = dateString;

                            viewModel.updateUiState(detailsState.copy(dateOfBirth = DOB));
                            coroutineScope.launch {
                                viewModel.updateUser()
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

        Spacer(modifier = Modifier.size(height = 30.dp, width = 0.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            //horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .width(280.dp)
                .height(40.dp)
                .clickable {
                    navigateToPreferences(viewModel.userUiState.userDetails.id)
                }

        ) {

            Icon(imageVector = Icons.Default.Settings, contentDescription = null, tint = PrimaryRed)
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "Change Preferences",
                color = PrimaryRed,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.size(height = 50.dp, width = 0.dp))

        Text(
            text = "Contact Us",
            color = PrimaryRed,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.size(height = 30.dp, width = 0.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(280.dp)
                .clickable {
                    val i = Intent(Intent.ACTION_SEND)
                    val emailAddress = arrayOf(creatorEmail)
                    i.putExtra(Intent.EXTRA_EMAIL, emailAddress)
                    i.setType("message/rfc822")
                    try {
                        context.startActivity(Intent.createChooser(i, "Choose an Email client : "))
                    } catch (s: SecurityException) {
                        Toast
                            .makeText(context, "An error occurred", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                .align(Alignment.Start)
        ) {
            Icon(imageVector = Icons.Default.Email, contentDescription = null, modifier = Modifier.size(50.dp), tint = PrimaryRed)
            Spacer(modifier = Modifier.size(width = 20.dp, height = 0.dp))
            Text(text = creatorEmail, color = PrimaryRed)
        }


        Spacer(modifier = Modifier.size(height = 20.dp, width = 0.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(280.dp)
                .clickable {
                    val number = Uri.parse("tel:$creatorPhoneNumber")
                    val i = Intent(Intent.ACTION_DIAL, number)
                    try {
                        context.startActivity(i)
                    } catch (s: SecurityException) {
                        Toast
                            .makeText(context, "An error occurred", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                .align(Alignment.Start)
        ) {
            Icon(Icons.Default.Phone, contentDescription = null, modifier = Modifier.size(40.dp), tint = PrimaryRed)
            Spacer(modifier = Modifier.size(width = 20.dp, height = 0.dp))
            Text(text = creatorPhoneNumber.toString(), color = PrimaryRed)
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
fun formatDate(state: DatePickerState): String {
    val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    val dateString: String = formatter.format(state.selectedDateMillis)
    return "${LocalDate.parse(dateString).year.toString()} ${LocalDate.parse(dateString).month.toString()} ${LocalDate.parse(dateString).dayOfMonth.toString()}"
}

fun formatDateFromString(date: String): String {
    //val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    //val dateString: String = formatter.format(state.selectedDateMillis)
    return "${LocalDate.parse(date).year} ${LocalDate.parse(date).month.toString()} ${LocalDate.parse(date).dayOfMonth.toString()}"
}
@Composable
fun ProfileImage() {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }


    val context = LocalContext.current


    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }


    imageUri?.let {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap.value = MediaStore.Images
                .Media.getBitmap(context.contentResolver, it)
        } else {
            val source = ImageDecoder
                .createSource(context.contentResolver, it)
            bitmap.value = ImageDecoder.decodeBitmap(source)

        }
    }

    // BITMAP SE PRETVORI U STRING PA ONDA U BAZU DA SE SPREMI
    if(bitmap.value != null) {
        bitmap.value?.let{
            btm ->
            Image(bitmap = btm.asImageBitmap(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, PrimaryRed, CircleShape)
                .clickable { launcher.launch("image/*") }
                )
        }
    } else{
        Image(
            painter = painterResource(id = R.drawable.profile_icon_design_free_vector),
            contentDescription = "",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, PrimaryRed, CircleShape)
                .clickable { launcher.launch("image/*") }
        )
    }
}


