package com.example.swimmers.data

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.swimtrip.data.Archive
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ViewModelScoped
class SwimmersRepository @Inject constructor(
    private val swimmersDao: SwimmersDao
) {
    fun getAllMembers(): Flow<List<Member>> {
        return swimmersDao.getAllMembers()
    }


    fun getAllChosenMembers(): Flow<List<Member>> {
        return swimmersDao.getAllChosenMembers()
    }


    suspend fun addNewMember(member: Member) {
        swimmersDao.addNewMember(member)
    }

    suspend fun isMemberExists(id: Int): Boolean {
        return swimmersDao.isMemberExists(id)
    }

    suspend fun isMemberChosen(id: Int): Boolean {
        return swimmersDao.isChosen(id)
    }

    suspend fun updateMember(member: Member) {
        swimmersDao.updateMember(member)
    }

    suspend fun deleteMemberById(memberId: Int) {
        swimmersDao.deleteMemberById(memberId)
    }

    suspend fun getChosenMembersCount(): Int {
        return swimmersDao.getChosenMembersCount()
    }

    suspend fun getChosenAndPaidMembersCount(): Int {
        return swimmersDao.getChosenAndPaidMembersCount()
    }


    suspend fun addArchive(archive: Archive) {
        swimmersDao.addArchive(archive)
    }

     fun getAllArchives(): Flow<List<Archive>> {
        return swimmersDao.getAllArchives()
    }
}

