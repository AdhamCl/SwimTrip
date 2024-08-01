package com.example.swimtrip.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay



@Composable
fun DeleteDialog(
    text: String,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
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
            modifier = Modifier.alpha(alphaState),
            onDismissRequest = {
                onDismissRequest()
            },
            title = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "تنبيه!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            },
            text = {
                Text(
                    text = text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
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
                    Text("إلغاء")
                }
            }
        )
    }
}
