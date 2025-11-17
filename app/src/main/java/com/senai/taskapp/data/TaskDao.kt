package com.senai.taskapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    // C - Create (Insert)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskEntity)

    // R - Read (Select All)
    @Query("SELECT * FROM task_table ORDER BY id DESC")
    fun getAllTasks(): Flow<List<TaskEntity>>

    // U - Update
    @Update
    suspend fun update(task: TaskEntity)

    // D - Delete
    @Delete
    suspend fun delete(task: TaskEntity)

    // D - Delete All (Opcional)
    @Query("DELETE FROM task_table")
    suspend fun deleteAll()
}