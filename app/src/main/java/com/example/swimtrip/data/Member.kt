package com.example.swimmers.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.swimtrip.Constants.DATABASE_MEMBERS_TABLE

@Entity(tableName = DATABASE_MEMBERS_TABLE)
data class Member(
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    val number: Int,
    val firstName: String,
    val lastName: String,
    val warning: Int,
    val isChosen: Boolean,
    val isPay: Boolean,
    val level:String
)
