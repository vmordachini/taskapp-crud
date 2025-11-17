package com.senai.taskapp

import android.app.Application
import com.senai.taskapp.data.TaskDatabase
import com.senai.taskapp.repository.TaskRepository

class TaskApplication : Application() {
    // Inicialização preguiçosa (lazy) do Database e Repository
    val database by lazy { TaskDatabase.getDatabase(this) }
    val repository by lazy { TaskRepository(database.taskDao()) }
}