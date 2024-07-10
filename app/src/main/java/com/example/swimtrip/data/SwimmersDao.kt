package com.example.swimmers.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
@Dao
interface SwimmersDao {

    @Query("SELECT * FROM members_table ORDER BY number ASC")
    fun getAllMembers(): Flow<List<Member>>


    @Query("SELECT * FROM members_table WHERE isChosen = 1 ORDER BY number ASC")
    fun getAllChosenMembers(): Flow<List<Member>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewMember(members: Member)

    @Query("SELECT EXISTS(SELECT 1 FROM members_table WHERE number = :number LIMIT 1)")
    suspend fun isMemberExists(number: Int): Boolean

    @Query("SELECT EXISTS(SELECT 1 FROM members_table WHERE id = :id AND isChosen = 1 LIMIT 1)")
    suspend fun isChosen(id: Int): Boolean
    @Update
    suspend fun updateMember(members: Member)

    @Query("DELETE FROM members_table WHERE id = :memberId")
    suspend fun deleteMemberById(memberId: Int)

    @Query("SELECT COUNT(*) FROM members_table WHERE isChosen = 1")
    suspend fun getChosenMembersCount(): Int

    @Query("SELECT COUNT(*) FROM members_table WHERE isChosen = 1 AND isPay = 1")
    suspend fun getChosenAndPaidMembersCount(): Int

}