package com.senai.taskapp

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.senai.taskapp.data.TaskEntity

class TaskAdapter(
    private val onTaskChecked: (TaskEntity) -> Unit,
    private val onDeleteClicked: (TaskEntity) -> Unit
) : ListAdapter<TaskEntity, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = getItem(position)
        holder.bind(currentTask)
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTaskTitle)
        private val cbCompleted: CheckBox = itemView.findViewById(R.id.cbTaskCompleted)
        private val btnDelete: ImageButton = itemView.findViewById(R.id.btnDeleteTask)

        fun bind(task: TaskEntity) {
            tvTitle.text = task.title
            cbCompleted.isChecked = task.isCompleted

            // Aplica ou remove o risco no texto (strikethrough)
            if (task.isCompleted) {
                tvTitle.paintFlags = tvTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                tvTitle.paintFlags = tvTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }

            // Listener para o CheckBox (operação de Update)
            // Remove o listener antigo para evitar chamadas múltiplas
            cbCompleted.setOnCheckedChangeListener(null)
            cbCompleted.isChecked = task.isCompleted

            cbCompleted.setOnCheckedChangeListener { _, isChecked ->
                // Cria uma nova TaskEntity com o status atualizado
                val updatedTask = task.copy(isCompleted = isChecked)
                onTaskChecked(updatedTask)
            }

            // Listener para o botão de Delete (operação de Delete)
            btnDelete.setOnClickListener {
                onDeleteClicked(task)
            }
        }
    }

    // Classe para otimizar a atualização do RecyclerView
    class TaskDiffCallback : DiffUtil.ItemCallback<TaskEntity>() {
        override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
            return oldItem == newItem
        }
    }
}