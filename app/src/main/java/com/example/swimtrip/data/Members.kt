package com.example.swimmers.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.swimmers.Constants.DATABASE_MEMBERS_TABLE

@Entity(tableName = DATABASE_MEMBERS_TABLE)
data class Members(
    @PrimaryKey
    val id: Int,
    val number: Int,
    val firstName: String,
    val lastName: String,
    val warning: Int,
    val isPay: Boolean
)
