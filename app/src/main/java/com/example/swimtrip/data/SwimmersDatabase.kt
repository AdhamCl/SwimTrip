package com.example.swimmers.data

import androidx.room.Database
import androidx.room.RoomDatabase



@Database(
    entities = [
        Members::class,

    ], version = 1, exportSchema = false
)

abstract class SwimmersDatabase : RoomDatabase() {
    abstract fun swimmersDao(): SwimmersDao
}