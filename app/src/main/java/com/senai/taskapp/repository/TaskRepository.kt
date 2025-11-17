package com.senai.taskapp.repository

import com.senai.taskapp.data.TaskDao
import com.senai.taskapp.data.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {
    // O Flow do DAO já é assíncrono e reativo
    val allTasks: Flow<List<TaskEntity>> = taskDao.getAllTasks()

    // Funções suspend para operações de escrita
    suspend fun insert(task: TaskEntity) {
        taskDao.insert(task)
    }

    suspend fun update(task: TaskEntity) {
        taskDao.update(task)
    }

    suspend fun delete(task: TaskEntity) {
        taskDao.delete(task)
    }
}