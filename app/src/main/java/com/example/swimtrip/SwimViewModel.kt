package com.example.swimtrip

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swimmers.data.Member
import com.example.swimmers.data.SwimmersRepository
import com.example.swimtrip.data.Archive
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class SwimViewModel @Inject constructor(
    private val swimmersRepository: SwimmersRepository
) : ViewModel() {

    val id: MutableState<Int> = mutableStateOf(0)
    val firstName: MutableState<String> = mutableStateOf("")
    val lastName: MutableState<String> = mutableStateOf("")
    val number: MutableState<Int> = mutableStateOf(0)
    val warning: MutableState<Int> = mutableStateOf(0)
    val isPay: MutableState<Boolean> = mutableStateOf(false)
    val isChosen: MutableState<Boolean> = mutableStateOf(true)

    val memberDeleteId: MutableState<Int> = mutableStateOf(0)
    val archiveDeleteId: MutableState<Int> = mutableStateOf(0)



    private val _allMembers = MutableStateFlow<List<Member>>(emptyList())
    var allMembers: StateFlow<List<Member>> = _allMembers

    fun getAllMembers() {
        viewModelScope.launch {
            swimmersRepository.getAllMembers().collect {
                _allMembers.value = it
            }

        }
    }

    private val _allChosenMembers = MutableStateFlow<List<Member>>(emptyList())
    var allChosenMembers: StateFlow<List<Member>> = _allChosenMembers

    fun getAllChosenMembers() {
        viewModelScope.launch {
            swimmersRepository.getAllChosenMembers().collect {
                _allChosenMembers.value = it
            }

        }
    }
    fun addMember(context: Context) {

            viewModelScope.launch(Dispatchers.IO) {

                val member = Member(
                    number = number.value,
                    firstName = firstName.value,
                    lastName = lastName.value,
                    warning = 0,
                    isChosen = false,
                    isPay = false
                )


                swimmersRepository.addNewMember(member)


            }

    }


    suspend fun checkMemberExists(number: Int): Boolean {
        return swimmersRepository.isMemberExists(number)
    }

    suspend fun checkMemberIsChosen(id: Int): Boolean {
        return swimmersRepository.isMemberChosen(id)
    }


    fun deleteMember() {
        viewModelScope.launch(Dispatchers.IO) {
            swimmersRepository.deleteMemberById(memberDeleteId.value)
        }
    }


    fun updateMember(member: Member) {
        viewModelScope.launch(Dispatchers.IO) {

            swimmersRepository.updateMember(member)
        }

    }

    fun updateAllMembers() {
        viewModelScope.launch {
            swimmersRepository.updateAllMembers(isPay = false, isChosen = false)
        }
    }


    fun addArchive() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentDate = LocalDate.now()
            val archive = Archive(
                date = currentDate.toString(), // This will format the date as "YYYY-MM-DD"
                members = allChosenMembers.value ?: emptyList() // Ensure members list is not null
            )

            swimmersRepository.addArchive(archive)
        }
    }

    private val _allArchive = MutableStateFlow<List<Archive>>(emptyList())
    var allArchive: StateFlow<List<Archive>> = _allArchive

    fun getAllArchive() {
        viewModelScope.launch {
            swimmersRepository.getAllArchives().collect {
                _allArchive.value = it
            }

        }
    }

    private val _selectedArchive  = MutableStateFlow<Archive?>(null)
    var selectedArchive : StateFlow<Archive?> = _selectedArchive

    fun getSelectedArchive(archiveId: Int) {
        viewModelScope.launch {
            swimmersRepository.getArchiveById(archiveId).collect {
                _selectedArchive.value = it
            }

        }
    }

    fun deleteArchive() {
        viewModelScope.launch(Dispatchers.IO) {
            swimmersRepository.deleteArchiveById(archiveDeleteId.value)
        }
    }



    fun restFiled(){
        firstName.value = ""
        lastName.value = ""
        number.value = 0
        warning.value = 0
        isChosen.value = false
        isPay.value = false
    }
}