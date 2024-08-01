package com.example.swimtrip.screens.archives

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swimmers.data.Member
import com.example.swimtrip.SwimViewModel
import com.example.swimtrip.data.Archive
import com.example.swimtrip.screens.AllMembers.AddNewMember
import com.example.swimtrip.screens.AllMembers.DeleteBackground
import com.example.swimtrip.screens.AllMembers.MemberItem
import com.example.swimtrip.ui.theme.MayaBlue
import kotlinx.coroutines.launch


@ExperimentalMaterial3Api
@Composable
fun ArchivesScreen(
    swimViewModel: SwimViewModel,
    openDialog: ()->Unit

) {

    val allArchive by swimViewModel.allArchive.collectAsState()
    val selectedArchive by swimViewModel.selectedArchive.collectAsState()
    val scope = rememberCoroutineScope()


    LaunchedEffect(key1 = true) {
        swimViewModel.getAllArchive()
    }



    val archiveDialog = remember { mutableStateOf(false) }

    when {
        archiveDialog.value -> {
            ArchiveDialog(
                archive = selectedArchive,
                onDismissRequest = {
                    archiveDialog.value = false
                }

            )

        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp, start = 2.dp, end = 2.dp, bottom = 5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),

        ) {
        items(
            items = allArchive,
            key = { it.id }
        ) {


            ArchiveItem(it,{
                archiveDialog.value = true
                scope.launch {
                        swimViewModel.getSelectedArchive(it)
                }
                }){

                openDialog()
                swimViewModel.archiveDeleteId.value = it.id
            }


        }
    }

}

@Composable
fun ArchiveItem(
    archive: Archive,
    archiveClicked: (Int)-> Unit,
    deleteArchive: ()->Unit
) {

    Card(
        modifier = Modifier
            .padding(top = 5.dp, end = 5.dp, start = 5.dp)
            .fillMaxWidth()
            .clickable { archiveClicked(archive.id) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp, end = 10.dp, start = 10.dp),
            Arrangement.Center,
            Alignment.CenterVertically
        ) {

            Text(
                modifier = Modifier.weight(1f),
                text = archive.id.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Text(
                modifier = Modifier.weight(4f),

                text = archive.date,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            IconButton(
                onClick = { deleteArchive() },
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