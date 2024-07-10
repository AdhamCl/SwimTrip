package com.example.swimtrip.screens.archives

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.swimmers.data.Member
import com.example.swimtrip.SwimViewModel
import com.example.swimtrip.screens.AllMembers.DeleteBackground
import com.example.swimtrip.screens.AllMembers.MemberItem


@ExperimentalMaterial3Api
@Composable
fun ArchivesScreen(
    swimViewModel: SwimViewModel

) {


}