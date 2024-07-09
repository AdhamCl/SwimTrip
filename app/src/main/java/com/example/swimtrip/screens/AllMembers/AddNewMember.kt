package com.example.swimtrip.screens.AllMembers

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.swimmers.data.Members
import kotlinx.coroutines.delay
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
) {

    var startAnimation by remember { mutableStateOf(false) }

    val alphaState by animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(100), label = ""
    )



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
                        value = number.toString(),

                        onValueChange = { numberChange(it.toInt()) },
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
                        onConfirmation()
                        onDismissRequest()
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
