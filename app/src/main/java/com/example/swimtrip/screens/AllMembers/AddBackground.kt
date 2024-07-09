package com.example.swimtrip.screens.AllMembers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp



@Composable
fun AddBackground(degrees: Float) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp, end = 5.dp, start = 5.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Green),

            contentAlignment = Alignment.CenterEnd
        ) {
            Icon(
                modifier = Modifier
                    .rotate(degrees)
                    .padding(end = 10.dp),
                imageVector = Icons.Filled.Delete,
                contentDescription = "",
                tint = Color.White
            )
        }
    }
}