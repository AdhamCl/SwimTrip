package com.example.swimtrip.data

import androidx.room.TypeConverter
import com.example.swimmers.data.Member
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromMemberList(members: List<Member>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Member>>() {}.type
        return gson.toJson(members, type)
    }

    @TypeConverter
    fun toMemberList(membersString: String): List<Member> {
        val gson = Gson()
        val type = object : TypeToken<List<Member>>() {}.type
        return gson.fromJson(membersString, type)
    }
}