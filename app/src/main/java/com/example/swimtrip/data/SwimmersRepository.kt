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

    fun getMembersByIntermediateLevel(): Flow<List<Member>> {
        return swimmersDao.getMembersByIntermediateLevel()
    }

    fun getMembersByPrimaryLevel(): Flow<List<Member>> {
        return swimmersDao.getMembersByPrimaryLevel()
    }

    fun getAllChosenMembers(): Flow<List<Member>> {
        return swimmersDao.getAllChosenMembers()
    }


    suspend fun addNewMember(member: Member) {
        swimmersDao.addNewMember(member)
    }

    suspend fun isMemberExists(id: Int,level:String): Boolean {
        return swimmersDao.isMemberExists(id,level)
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

    suspend fun updateAllMembers(isPay: Boolean, isChosen: Boolean) {
        swimmersDao.updateAllMembers(isPay, isChosen)
    }

    suspend fun addArchive(archive: Archive) {
        swimmersDao.addArchive(archive)
    }

    fun getAllArchives(): Flow<List<Archive>> {
        return swimmersDao.getAllArchives()
    }

    fun getArchiveById(id: Int): Flow<Archive?> {
        return swimmersDao.getArchiveById(id)
    }

    suspend fun deleteArchiveById(archiveId: Int) {
        swimmersDao.deleteArchiveById(archiveId)
    }
}

