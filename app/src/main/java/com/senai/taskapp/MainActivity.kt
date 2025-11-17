package com.senai.taskapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.senai.taskapp.viewmodel.TaskViewModel
import com.senai.taskapp.viewmodel.TaskViewModelFactory

class MainActivity : AppCompatActivity() {

    // Inicializa o ViewModel usando a Factory
    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory((application as TaskApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Substitua o R.layout.activity_main pelo layout que você usa
        // Se seu projeto usa Compose, esta parte será diferente.
        // Assumindo que você está usando XML como no guia:
        setContentView(R.layout.activity_main)

        // Referências da UI
        val etTaskTitle: EditText = findViewById(R.id.etTaskTitle)
        val btnAddTask: Button = findViewById(R.id.btnAddTask)
        val rvTasks: RecyclerView = findViewById(R.id.rvTasks)

        // 1. Configuração do Adapter
        val adapter = TaskAdapter(
            onTaskChecked = { task ->
                // Chamada de Update
                taskViewModel.update(task)
            },
            onDeleteClicked = { task ->
                // Chamada de Delete
                taskViewModel.delete(task)
            }
        )
        rvTasks.adapter = adapter

        // 2. Observação do LiveData (Flow)
         taskViewModel.allTasks.observe(this) { tasks ->
            // Quando a lista de tarefas muda no banco de dados, o LiveData notifica a UI
            tasks?.let {
                adapter.submitList(it)
            }
        }

        // 3. Listener do Botão Adicionar (operação de Insert)
        btnAddTask.setOnClickListener {
            val title = etTaskTitle.text.toString().trim()
            if (title.isNotEmpty()) {
                taskViewModel.insert(title)
                etTaskTitle.setText("") // Limpa o campo
            }
        }
    }
}