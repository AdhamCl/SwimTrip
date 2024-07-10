package com.example.swimtrip.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SwimFAB(addNewMember: () -> Unit) {

    FloatingActionButton(
        onClick = {
            addNewMember()
        },
        containerColor = Color.Transparent,
        contentColor = Color.Blue

        ) {
        Row(
            modifier =  Modifier.padding(10.dp),

            ){

            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add Button",
                tint = Color.White,
            )
        }
    }

}
