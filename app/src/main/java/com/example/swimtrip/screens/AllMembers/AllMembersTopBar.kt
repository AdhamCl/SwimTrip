package com.example.swimtrip.screens.AllMembers

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swimtrip.ui.theme.MayaBlue
import com.example.swimtrip.ui.theme.Pink80
import com.example.swimtrip.ui.theme.YaleBlue

@Composable
fun AllMembersTopBar(
    level:String,
    levelChange:(String)->Unit,
    openDialog: () -> Unit,
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier.weight(2f),
            text = "السباحين",
            fontSize = 22.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Box(
            modifier = Modifier.weight(6f),
            contentAlignment = Alignment.Center
        ) {
            ToggleButton(level,levelChange)
        }

        IconButton(
            onClick = { openDialog() },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Add to Other",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )

        }

    }
}

@Composable
fun ToggleButton(
    level:String,
    levelChange:(String)->Unit,
) {
    var selected by remember { mutableStateOf(level) }

    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        Button(
            onClick = { selected = "متوسط";levelChange("متوسط")},
            colors = ButtonDefaults.buttonColors(
                  if (selected == "متوسط") Pink80 else MayaBlue
            ),
            shape = RoundedCornerShape(topStartPercent = 50, bottomStartPercent = 50),
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "متوسط", color = Color.White, fontWeight = FontWeight.Bold)
        }

        Button(
            onClick = { selected = "ابتدائي";levelChange("ابتدائي") },
            colors = ButtonDefaults.buttonColors(
                  if (selected == "ابتدائي") Pink80 else MayaBlue
            ),
            shape = RoundedCornerShape(topEndPercent = 50, bottomEndPercent = 50),
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "ابتدائي", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}