package com.example.swimtrip.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swimtrip.SwimViewModel
import com.example.swimtrip.screens.AllMembers.AddNewMember
import com.example.swimtrip.screens.AllMembers.MembersScreen
import com.example.swimtrip.screens.archives.ArchivesScreen
import com.example.swimtrip.screens.chosen.ChosenScreen


@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Composable
fun HomeScreen(swimViewModel: SwimViewModel) {

    val tabItem = listOf(
        TabItem(
            title = "الأعضاء" ,
            index = 0 ,
        ) ,
        TabItem(
            title = "المعنيون" ,
            index = 1 ,
        ) ,
        TabItem(
            title = "الارشيف" ,
            index = 2 ,
        )
    )

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { tabItem.size }
    LaunchedEffect(key1 = selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(key1 = pagerState.currentPage , pagerState.isScrollInProgress) {
        if (! pagerState.isScrollInProgress)
            selectedTabIndex = pagerState.currentPage
    }
    LaunchedEffect(key1 = selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    val editNameDialog = remember { mutableStateOf(false) }

    when {
        editNameDialog.value -> {
            AddNewMember(
                onDismissRequest = { editNameDialog.value = false },
                onConfirmation = {
                    editNameDialog.value = false
                },
                firstName = swimViewModel.firstName.value,
                firstNameChange = { swimViewModel.firstName.value = it },
                lastName = swimViewModel.lastName.value,
                lastNameChange = { swimViewModel.lastName.value = it },
                number = swimViewModel.number.value,
                numberChange = { swimViewModel.number.value = it }
                )

        }
    }
    Scaffold(
        floatingActionButton = {

            SessionsFAB({ editNameDialog.value = true })


        } ,
        topBar = {
            TopAppBar(

                title = {
                    Row(Modifier.fillMaxWidth(),Arrangement.Center) {
                        Text("السباحين", fontSize = 22.sp,color = Color.White, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(Color.Blue),


            )
        } ,
        modifier = Modifier.fillMaxSize() ,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)

        ) {

            TabRow(
                selectedTabIndex = selectedTabIndex
            ) {
                tabItem.forEachIndexed { index , item ->
                    Tab(
                        selected = index == selectedTabIndex ,
                        onClick = {
                            selectedTabIndex = index
                        } ,
                        text = {
                            Text(item.title)
                        } ,
                    )
                }

            }

            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight() //and this
                    .weight(1f) ,
                state = pagerState
            ) { index ->

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f) ,
                ) {
                    when (index) {
                        0 -> MembersScreen(swimViewModel)
                        1 -> ChosenScreen()
                        2 -> ArchivesScreen()
                    }


                }
            }
        }
    }

}