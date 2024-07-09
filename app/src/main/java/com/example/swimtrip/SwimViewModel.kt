package com.example.swimtrip

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swimmers.data.Members
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
    val isChosen: MutableState<Boolean> = mutableStateOf(false)


    private val _allMembers = MutableStateFlow<List<Members>>(emptyList())
    var allMembers: StateFlow<List<Members>> = _allMembers

    fun getAllMembers() {
        viewModelScope.launch {
            swimmersRepository.getAllMembers().collect {
                _allMembers.value = it
            }

        }
    }

    private val _allChosenMembers = MutableStateFlow<List<Members>>(emptyList())
    var allChosenMembers: StateFlow<List<Members>> = _allChosenMembers

    fun getChosenMembers() {
        viewModelScope.launch {
            swimmersRepository.getChosenMembers().collect {
                _allChosenMembers.value = it
            }

        }
    }

    fun addDate() {
        viewModelScope.launch(Dispatchers.IO) {

            val member = Members(
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


    fun deleteMember(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            swimmersRepository.deleteMemberById(id)
        }
    }


    fun updateMember() {
        viewModelScope.launch(Dispatchers.IO) {
            val member = Members(
                number = number.value,
                firstName = firstName.value,
                lastName = lastName.value,
                warning = warning.value,
                isChosen = isChosen.value,
                isPay = isPay.value
            )
            swimmersRepository.updateMember(member)
        }

    }


}