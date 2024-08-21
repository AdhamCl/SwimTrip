package com.example.swimmers.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.swimtrip.data.Archive
import kotlinx.coroutines.flow.Flow
@Dao
interface SwimmersDao {

    @Query("SELECT * FROM members_table ORDER BY number ASC")
    fun getAllMembers(): Flow<List<Member>>

    @Query("SELECT * FROM members_table WHERE level = 'متوسط' ORDER BY number ASC")
    fun getMembersByIntermediateLevel(): Flow<List<Member>>

    @Query("SELECT * FROM members_table WHERE level = 'ابتدائي' ORDER BY number ASC")
    fun getMembersByPrimaryLevel(): Flow<List<Member>>


    @Query("""
        SELECT * FROM members_table 
        WHERE isChosen = 1 
        ORDER BY 
            CASE 
                WHEN level = 'متوسط' THEN 0 
                WHEN level = 'ابتدائي' THEN 1 
                ELSE 2 
            END, 
            number ASC
    """)
    fun getAllChosenMembers(): Flow<List<Member>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewMember(members: Member)

    @Query("""
        SELECT EXISTS(
            SELECT 1 
            FROM members_table 
            WHERE number = :number 
              AND level = :level 
            LIMIT 1
        )
    """)
    suspend fun isMemberExists(number: Int, level: String): Boolean

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

    @Query("UPDATE members_table SET isPay = :isPay, isChosen = :isChosen")
    suspend fun updateAllMembers(isPay: Boolean, isChosen: Boolean)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArchive(archive: Archive)

    @Query("SELECT * FROM archive_table ORDER BY id ASC")
     fun getAllArchives(): Flow<List<Archive>>

    @Query("SELECT * FROM archive_table WHERE id = :id LIMIT 1")
     fun getArchiveById(id: Int): Flow<Archive?>

    @Query("DELETE FROM archive_table WHERE id = :archiveId")
    suspend fun deleteArchiveById(archiveId: Int)
}