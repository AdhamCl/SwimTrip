package com.example.swimtrip.screens.chosen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swimmers.data.Member
import com.example.swimtrip.R
import com.example.swimtrip.ui.theme.MayaBlue

@Composable
fun ChosenTopBar(
    chosenAndPaidMembersCount: Int,
    chosenMembersCount: Int,
    archiveClick: ()-> Unit
) {





    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier.weight(5f),
            text = "المختارون : ${chosenMembersCount} ",
            fontSize = 22.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.weight(5f),
            text = "المانحون : ${chosenAndPaidMembersCount}",
            fontSize = 22.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        IconButton(
            onClick = { archiveClick() },
            modifier = Modifier
        ) {
            Icon(
                painter = painterResource(id = R.drawable.archive),
                contentDescription =  "Delete Member",
                tint = Color.White,
                modifier = Modifier
                    .size(28.dp)
                    .weight(2f)
            )
        }


    }
}