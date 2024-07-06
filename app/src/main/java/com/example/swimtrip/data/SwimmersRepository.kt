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
    fun getAllMembers(): Flow<List<Members>>{
        return  swimmersDao.getAllMembers()
    }


    fun getPayMembers(): Flow<List<Members>>{
        return swimmersDao.getPayMembers()
    }


    suspend fun addNewMember(member: Members){
        swimmersDao.addNewMember(member)
    }

    suspend fun updateMember(member: Members){
        swimmersDao.updateMember(member)
    }

    suspend fun deleteMemberById(memberId: Int){
        swimmersDao.deleteMemberById(memberId)
    }
}