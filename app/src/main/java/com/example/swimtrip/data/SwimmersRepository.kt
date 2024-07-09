package com.example.swimmers.data

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ViewModelScoped
class SwimmersRepository @Inject constructor(
    private val swimmersDao: SwimmersDao
) {
    fun getAllMembers(): Flow<List<Member>>{
        return  swimmersDao.getAllMembers()
    }


    fun getChosenMembers(): Flow<List<Member>>{
        return swimmersDao.getChosenMembers()
    }


    suspend fun addNewMember(member: Member){
        swimmersDao.addNewMember(member)
    }

    suspend fun isMemberExists(number: Int): Boolean {
        return swimmersDao.isMemberExists(number)
    }

    suspend fun updateMember(member: Member){
        swimmersDao.updateMember(member)
    }

    suspend fun deleteMemberById(memberId: Int){
        swimmersDao.deleteMemberById(memberId)
    }
}