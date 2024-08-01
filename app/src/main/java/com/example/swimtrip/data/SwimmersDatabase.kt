package com.example.swimmers.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.swimtrip.data.Archive
import com.example.swimtrip.data.Converters


@Database(
    entities = [
        Member::class,
        Archive::class
    ], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)

abstract class SwimmersDatabase : RoomDatabase() {
    abstract fun swimmersDao(): SwimmersDao
}