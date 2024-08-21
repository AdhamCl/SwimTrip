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
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swimmers.data.Member
import com.example.swimtrip.R
import com.example.swimtrip.SwimViewModel
import com.example.swimtrip.ui.theme.MayaBlue
import kotlinx.coroutines.launch


@ExperimentalMaterial3Api
@Composable
fun MembersScreen(
    swimViewModel: SwimViewModel,
    openWarningDialog: () -> Unit,
    openDeleteDialog: () -> Unit
) {

    val allPrimaryMembers by swimViewModel.allPrimaryMembers.collectAsState()
    val allIntermediateMembers by swimViewModel.allIntermediateMembers.collectAsState()

    val level by swimViewModel.level.collectAsState()



    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        swimViewModel.getMembersByPrimaryLevel()
        swimViewModel.getMembersByIntermediateLevel()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp, start = 2.dp, end = 2.dp, bottom = 5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),

        ) {
        items(
            items = if(level=="متوسط")allIntermediateMembers else allPrimaryMembers,
            key = { it.id }
        ) {


            MemberItem(it,
                {
                    openWarningDialog()
                    swimViewModel.id.value = it.id
                    swimViewModel.number.value = it.number
                    swimViewModel.firstName.value = it.firstName
                    swimViewModel.lastName.value = it.lastName
                    swimViewModel.warning.value = it.warning
                    swimViewModel.isChosen.value = it.isChosen
                    swimViewModel.isPay.value = it.isPay
                }, {
                    swimViewModel.memberDeleteId.value = it.id
                    openDeleteDialog()

                }) {
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
                            level = it.level
                        )
                        swimViewModel.updateMember(member)
                    } else
                        Toast.makeText(context, "لقد قمت بأضفة هاذا العضو مسبقا", Toast.LENGTH_SHORT).show()

                }
            }


        }
    }

}

@Composable
fun MemberItem(
    member: Member,
    openWarningClicked: () -> Unit,
    deleteMember: () -> Unit,
    onAddClicked: () -> Unit
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
            IconButton(
                onClick = { onAddClicked() },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add to Other",
                    tint = MayaBlue,
                    modifier = Modifier.size(32.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = member.number.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.width(10.dp))


            Column(
                modifier = Modifier.weight(4f),

                ) {
                Text(
                    text = member.firstName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = member.lastName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )


            }



            Text(
                modifier = Modifier.weight(1f),
                text = member.warning.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            IconButton(
                onClick = {openWarningClicked() },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.alerte),
                    contentDescription = "Add to Other",
                    tint = MayaBlue,
                    modifier = Modifier.size(28.dp)
                )
            }


            IconButton(
                onClick = { deleteMember() },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Delete Member",
                    tint = MayaBlue,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}