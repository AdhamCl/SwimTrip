package com.example.swimtrip.screens.AllMembers


import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swimmers.data.Member
import com.example.swimtrip.SwimViewModel
import com.example.swimtrip.ui.theme.MayaBlue
import kotlinx.coroutines.launch


@ExperimentalMaterial3Api
@Composable
fun MembersScreen(
    swimViewModel: SwimViewModel
) {

    val allMembers by swimViewModel.allMembers.collectAsState()



    val context = LocalContext.current
    val scope = rememberCoroutineScope()

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
            val isDismiss = dismissState.isDismissed(DismissDirection.EndToStart)
            if (isDismiss && dismissDirection == DismissDirection.EndToStart) {
                swimViewModel.deleteMember(it.id)
            }
            val degrees: Float by animateFloatAsState(
                if (dismissState.targetValue == DismissValue.Default) 0f else -45f,
                label = ""
            )



            SwipeToDismiss(
                state = dismissState,
                background = { DeleteBackground(degrees) },
                directions = setOf(DismissDirection.EndToStart),
                dismissContent = {
                    MemberItem(it) {
                        scope.launch {

                            if (!swimViewModel.checkMemberIsChosen(it.id)) {
                                swimViewModel.isChosen.value = true
                                val member = Member(
                                    id = it.id,
                                    number = it.number,
                                    firstName = it.firstName,
                                    lastName = it.lastName,
                                    warning = it.warning,
                                    isChosen = true,
                                    isPay = false,
                                )
                                swimViewModel.updateMember(member)
                            } else
                                Toast.makeText(context, "", Toast.LENGTH_SHORT).show()

                        }
                    }
                }
            )

        }
    }

}

@Composable
fun MemberItem(
    member: Member,
    onAddClicked:()->Unit
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
                .padding(10.dp),
            Arrangement.Center,
            Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = member.number.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

                Text(
                    modifier = Modifier.weight(4f),

                    text = member.firstName+" "+member.lastName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )



            Text(
                modifier = Modifier.weight(.5f),
                text = member.warning.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            IconButton(
                onClick = { onAddClicked() },
                modifier = Modifier.weight(.5f)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add to Other",
                    tint = MayaBlue,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}