package com.example.swimtrip

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swimmers.data.Members
import com.example.swimmers.data.SwimmersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SwimViewModel @Inject constructor(
    private val swimmersRepository : SwimmersRepository
) : ViewModel() {



    private val _allMembers = MutableStateFlow<List<Members>>(emptyList())
    var allMembers : StateFlow<List<Members>> = _allMembers

    val firstName: MutableState<String> = mutableStateOf("")
    val lastName: MutableState<String> = mutableStateOf("")
    val number: MutableState<Int> = mutableStateOf(0)
    fun getAllMembers()
    {
        viewModelScope.launch {
            swimmersRepository.getAllMembers().collect {
                _allMembers.value = it
            }

        }
    }

}