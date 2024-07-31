package com.example.swimtrip.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.swimmers.data.Member
import com.example.swimtrip.Constants.DATABASE_ARCHIVE_TABLE


@Entity(tableName = DATABASE_ARCHIVE_TABLE)
data class Archive(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,
    val members: List<Member>
)
