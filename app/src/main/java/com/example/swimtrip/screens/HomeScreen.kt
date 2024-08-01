package com.example.swimtrip.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.example.swimmers.data.Member
import com.example.swimtrip.SwimViewModel
import com.example.swimtrip.screens.AllMembers.AddNewMember
import com.example.swimtrip.screens.AllMembers.AllMembersTopBar
import com.example.swimtrip.screens.AllMembers.MembersScreen
import com.example.swimtrip.screens.AllMembers.WarningDialog
import com.example.swimtrip.screens.archives.ArchivesScreen
import com.example.swimtrip.screens.archives.ArchivesTopBar
import com.example.swimtrip.screens.chosen.ArchiveDialog
import com.example.swimtrip.screens.chosen.ChosenScreen
import com.example.swimtrip.screens.chosen.ChosenTopBar
import com.example.swimtrip.ui.theme.MayaBlue


@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Composable
fun HomeScreen(swimViewModel: SwimViewModel) {

    val tabItem = listOf(
        TabItem(
            title = "الأعضاء",
            index = 0,
        ),
        TabItem(
            title = "المعنيون",
            index = 1,
        ),
        TabItem(
            title = "الارشيف",
            index = 2,
        )
    )

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { tabItem.size }
    LaunchedEffect(key1 = selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress)
            selectedTabIndex = pagerState.currentPage
    }
    LaunchedEffect(key1 = selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    val context = LocalContext.current


    val allChosenMembers by swimViewModel.allChosenMembers.collectAsState()


    var chosenAndPaidMembersCount by remember { mutableIntStateOf(0) }
    var chosenMembersCount by remember { mutableIntStateOf(0) }


    LaunchedEffect(key1 = true) {
        swimViewModel.getAllChosenMembers()
    }


    val editNameDialog = remember { mutableStateOf(false) }
    val archiveDialog = remember { mutableStateOf(false) }
    val archiveDeleteDialog = remember { mutableStateOf(false) }
    val isChosenMemberDeleteDialog = remember { mutableStateOf(false) }
    val memberDeleteDialog = remember { mutableStateOf(false) }
    val warningDialog = remember { mutableStateOf(false) }
    when {
        editNameDialog.value -> {
            AddNewMember(
                onDismissRequest = {
                    editNameDialog.value = false
                },
                onConfirmation = {
                    swimViewModel.addMember(context)
                },
                firstName = swimViewModel.firstName.value,
                firstNameChange = { swimViewModel.firstName.value = it },
                lastName = swimViewModel.lastName.value,
                lastNameChange = { swimViewModel.lastName.value = it },
                number = swimViewModel.number.value,
                numberChange = { swimViewModel.number.value = it },
                checkMemberExists = { swimViewModel.checkMemberExists(it) },
            )

        }

        archiveDialog.value -> {
            ArchiveDialog(
                onDismissRequest = {
                    archiveDialog.value = false
                },
                onConfirmation = {
                    swimViewModel.addArchive()
                    swimViewModel.updateAllMembers()
                },
                listsSize = allChosenMembers.size,
                context = context

            )
        }

        archiveDeleteDialog.value -> {
            DeleteDialog("", { archiveDeleteDialog.value = false }) {

            }
        }

        isChosenMemberDeleteDialog.value -> {
            DeleteDialog("هل أنت متأكد تريد حذف هاذا الرشيف",
                { isChosenMemberDeleteDialog.value = false }) {
                swimViewModel.deleteArchive()
            }
        }

        memberDeleteDialog.value -> {
            DeleteDialog("هل أنت متأكد تريد حذف هاذا العضو", { memberDeleteDialog.value = false }) {
                swimViewModel.deleteMember()
            }
        }
        warningDialog.value -> {
            WarningDialog(swimViewModel.warning.value){
                warningDialog.value = false
                swimViewModel.warning.value = it
                val member = Member(
                    id = swimViewModel.id.value,
                    number = swimViewModel.number.value,
                    firstName = swimViewModel.firstName.value,
                    lastName = swimViewModel.lastName.value,
                    warning = swimViewModel.warning.value,
                    isChosen = swimViewModel.isChosen.value,
                    isPay = swimViewModel.isPay.value,
                )
                swimViewModel.updateMember(member)
                swimViewModel.getAllMembers()
            }


        }

    }



    chosenMembersCount = allChosenMembers.size
    chosenAndPaidMembersCount = allChosenMembers.count { it.isPay }
    Scaffold(
        topBar = {
            TopAppBar(

                title = {
                    when (selectedTabIndex) {
                        0 -> AllMembersTopBar {
                            editNameDialog.value = true
                            swimViewModel.restFiled()
                        }

                        1 -> ChosenTopBar(
                            chosenAndPaidMembersCount,
                            chosenMembersCount,
                            { archiveDialog.value = true }
                        )

                        2 -> ArchivesTopBar()
                    }

                },
                colors = TopAppBarDefaults.topAppBarColors(MayaBlue),


                )
        },
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)

        ) {

            TabRow(
                selectedTabIndex = selectedTabIndex,
                contentColor = MayaBlue,
            ) {
                tabItem.forEachIndexed { index, item ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = {
                            selectedTabIndex = index
                        },
                        text = {
                            Text(
                                text = item.title,
                                fontWeight = FontWeight.Bold,
                                color = MayaBlue
                            )
                        },
                    )
                }

            }



            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
            ) {
                when (selectedTabIndex) {
                    0 -> MembersScreen(swimViewModel,{
                        warningDialog.value = true
                    }) {
                        memberDeleteDialog.value = true
                    }

                    1 -> ChosenScreen(
                        allChosenMembers)
                        { swimViewModel.updateMember(it) }



                    2 -> ArchivesScreen(swimViewModel, { isChosenMemberDeleteDialog.value = true })
                }


            }
        }

    }
}