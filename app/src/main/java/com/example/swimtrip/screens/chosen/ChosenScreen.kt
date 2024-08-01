package com.example.swimtrip.screens.chosen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swimmers.data.Member
import com.example.swimtrip.screens.AllMembers.DeleteBackground
import com.example.swimtrip.ui.theme.MayaBlue


@ExperimentalMaterial3Api
@Composable
fun ChosenScreen(
    allChosenMembers: List<Member>,
    updateMember: (Member) -> Unit,
) {


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp, start = 2.dp, end = 2.dp, bottom = 5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),

        ) {
        items(
            items = allChosenMembers,
            key = { it.id }
        ) {



                    MemberChosenItem(it,{
                        val member = Member(
                            id = it.id,
                            number = it.number,
                            firstName = it.firstName,
                            lastName = it.lastName,
                            warning = it.warning,
                            isChosen = false,
                            isPay = false,
                        )
                        updateMember(member)
                    }) { onCheckedChange ->
                        val member = Member(
                            id = it.id,
                            number = it.number,
                            firstName = it.firstName,
                            lastName = it.lastName,
                            warning = it.warning,
                            isChosen = it.isChosen,
                            isPay = onCheckedChange,
                        )
                        updateMember(member)








                }


        }

    }
}

@Composable
fun MemberChosenItem(
    member: Member,
    deleteMember:()->Unit,

    onCheckedChange: (Boolean) -> Unit
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
                .padding(top = 10.dp, bottom = 10.dp, end = 10.dp, start = 10.dp),
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

                text = member.firstName + " " + member.lastName,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

 

            Checkbox(
                checked = member.isPay,
                onCheckedChange = { onCheckedChange(it) },
                modifier = Modifier.size(24.dp)
            )
            
            Spacer(modifier = Modifier.width(20.dp))

            IconButton(
                onClick = { deleteMember() },
                modifier = Modifier
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription =  "Delete Member",
                    tint = MayaBlue,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}



