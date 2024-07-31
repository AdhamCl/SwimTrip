package com.example.swimtrip.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swimtrip.data.Archive
import com.example.swimtrip.screens.archives.MemberItem
import kotlinx.coroutines.delay



@Composable
fun DeleteDialog(
    onDismissRequest: () -> Unit
) {


    var startAnimation by remember { mutableStateOf(false) }

    val alphaState by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(100), label = ""
    )



    Box(

    ) {
        LaunchedEffect(key1 = Unit) {
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
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "قائمة المعنيون",
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                }
            },
            text = {
                if (archive?.members.isNullOrEmpty()) {
                    Text(text = "قائمة فارغة.")
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        items(
                            items = archive!!.members,
                            key = { it.id }
                        ) { member ->
                            MemberItem(member)
                        }
                    }
                }
            },
            confirmButton = {

            },
            dismissButton = {

            }
        )
    }
}