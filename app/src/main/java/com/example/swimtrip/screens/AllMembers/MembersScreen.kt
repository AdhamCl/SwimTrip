package com.example.swimtrip.screens.AllMembers


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.swimmers.data.Members
import com.example.swimtrip.SwimViewModel


@ExperimentalMaterial3Api
@Composable
fun MembersScreen(
    swimViewModel: SwimViewModel
) {

    val allMembers by  swimViewModel.allMembers.collectAsState()





    LaunchedEffect(key1 = true) {
        swimViewModel.getAllMembers()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp, start = 2.dp, end = 2.dp, bottom = 5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),

        ) {
        items(
            items = allMembers,
            key = { it.id }
        ) {


            val dismissState = rememberDismissState()
            val dismissDirection = dismissState.dismissDirection

            val isDismissed = dismissState.isDismissed(DismissDirection.StartToEnd)

            val degrees: Float by animateFloatAsState(
                if (dismissState.targetValue == DismissValue.Default) 0f else -45f,
                label = ""
            )


            if (isDismissed && dismissDirection == DismissDirection.EndToStart) {
                swimViewModel.deleteMember(it.id)
            }


            SwipeToDismiss(
                state = dismissState,
                background = {  DeleteBackground(degrees)    },
                directions = setOf(DismissDirection.EndToStart),
                dismissContent = {
                    MemberItem(it)

                }
            )

        }
    }

}

@Composable
fun MemberItem(
    member: Members,

) {
    Card(
        modifier = Modifier
            .padding(top = 5.dp, end = 5.dp, start = 5.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            Arrangement.Center,
            Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = member.number.toString()
            )
            Column(
                modifier = Modifier.weight(3f),
                ) {
                Text(
                    text = member.firstName
                )
                Text(
                    text = member.lastName
                )
            }

            Text(
                modifier = Modifier.weight(0.5f),
                text = member.warning.toString()
            )
            IconButton(
                onClick = {  },
                modifier = Modifier.weight(0.5f)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add to Other", tint = Color.Green)
            }
        }
    }
}