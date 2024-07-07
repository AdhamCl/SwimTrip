package com.example.swimtrip.screens.AllMembers

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.swimmers.data.Members
import com.example.swimtrip.SwimViewModel


@Composable
fun MembersScreen(
    swimViewModel: SwimViewModel
) {

    val allMembers by  swimViewModel.allMembers.collectAsState()





    LaunchedEffect(key1 = true) {
        swimViewModel.getAllMembers()
    }

    LazyColumn(
        modifier = Modifier.padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),

        ) {
        items(
            items = allMembers,
            key = { it.id }
        ) {


            MemberItem(it)
        }
    }

}

@Composable
fun MemberItem(member: Members) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "ID: ${member.id}")
            Text(text = "Number: ${member.number}")
            Text(text = "Name: ${member.firstName} ${member.lastName}")
            Text(text = "Warnings: ${member.warning}")
            Text(text = "Paid: ${if (member.isPay) "Yes" else "No"}")
        }
    }
}