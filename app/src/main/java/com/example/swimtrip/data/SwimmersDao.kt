package com.example.swimmers.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
@Dao
interface SwimmersDao {

    @Query("SELECT * FROM members_table ORDER BY id ASC")
    fun getAllMembers(): Flow<List<Members>>


    @Query("SELECT * FROM members_table WHERE isPay = 1 ORDER BY id ASC")
    fun getPayMembers(): Flow<List<Members>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewMember(members: Members)

    @Update
    suspend fun updateMember(members: Members)

    @Query("DELETE FROM members_table WHERE id = :memberId")
    suspend fun deleteMemberById(memberId: Int)

}