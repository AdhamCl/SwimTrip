package com.example.swimtrip

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swimmers.data.Member
import com.example.swimmers.data.SwimmersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SwimViewModel @Inject constructor(
    private val swimmersRepository: SwimmersRepository
) : ViewModel() {


    val firstName: MutableState<String> = mutableStateOf("")
    val lastName: MutableState<String> = mutableStateOf("")
    val number: MutableState<Int> = mutableStateOf(0)
    val warning: MutableState<Int> = mutableStateOf(0)
    val isPay: MutableState<Boolean> = mutableStateOf(false)
    val isChosen: MutableState<Boolean> = mutableStateOf(true)



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

    fun addDate() {
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


    fun deleteMember(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            swimmersRepository.deleteMemberById(id)
        }
    }


    fun updateMember(member: Member) {
        viewModelScope.launch(Dispatchers.IO) {

            swimmersRepository.updateMember(member)
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