package com.example.todo_kotlin

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface Dao {

    @Insert
    suspend fun inserttask(entity: Entity)

    @Update
    suspend fun updatetask(entity: Entity)

    @Delete
    suspend fun deletetask(entity: Entity)

    @Query("Delete from to_do")
    suspend fun deleteall()

    @Query("select * from to_do")
    suspend fun getTasks(): List<CardInfo>

}