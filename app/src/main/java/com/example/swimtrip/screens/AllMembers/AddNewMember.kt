package com.example.swimtrip.screens.AllMembers

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AddNewMember(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    firstName: String,
    firstNameChange: (String) -> Unit,
    lastName: String,
    lastNameChange: (String) -> Unit,
    number: Int,
    numberChange: (Int) -> Unit,
    checkMemberExists: suspend  (Int) -> Boolean,
) {

    var startAnimation by remember { mutableStateOf(false) }

    val alphaState by animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(100), label = ""
    )

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Box(

    ){
        LaunchedEffect(key1 = Unit){
            delay(100)
            startAnimation = true
        }
        AlertDialog(
            modifier = Modifier
                .alpha(alphaState),
            title = {
                Text(text = "تغير الاسم و اللقب")
            },
            text = {
                Column {

                    OutlinedTextField(
                        value = firstName,

                        onValueChange = { firstNameChange(it) },
                        label = {
                            Text(
                                text = "الأسم",
                            )

                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        singleLine = true,

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),

                        )

                    OutlinedTextField(
                        value = lastName,

                        onValueChange = { lastNameChange(it) },
                        label = {
                            Text(
                                text = "اللفب",
                            )

                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        singleLine = true,

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),

                        )
                    OutlinedTextField(
                        value = if (number == 0) "" else number.toString(),

                        onValueChange = {
                            val newNumber = it.toIntOrNull()

                            if (newNumber != null) {
                                numberChange(newNumber)
                            } else {
                                numberChange(0)
                            }
                                        },
                        label = {
                            Text(
                                text = "الرقم",
                            )

                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),

                        )
                }
            },
            onDismissRequest = {
                onDismissRequest()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if(firstName.isEmpty()) Toast.makeText(context,"الاسم فارغ",Toast.LENGTH_SHORT).show()
                        else if(lastName.isEmpty()) Toast.makeText(context,"اللقب فارغ",Toast.LENGTH_SHORT).show()
                        else if(number==0)Toast.makeText(context,"الرقم فارغ",Toast.LENGTH_SHORT).show()
                        else {
                            scope.launch {
                                if (checkMemberExists(number)) Toast.makeText(
                                    context,
                                    "هاذا الرقم موجود بالفعل",
                                    Toast.LENGTH_SHORT
                                ).show()
                                else {
                                    onConfirmation(); onDismissRequest()
                                }
                            }
                        }
                    }
                ) {
                    Text("تأكيد")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text("الغاء")
                }
            }
        )}
}

@Composable
fun ShowToast(context: Context,text:String) {
    Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
}
