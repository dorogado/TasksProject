package sceens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.models.Project
import data.models.ProjectBoard
import data.models.Task

@Composable
fun TaskBoardScreen(
    project: Project,
    board: ProjectBoard,
    isLoading: Boolean,
    onBack: () -> Unit,
    onAddTask: (String) -> Unit,
    message: String?,
    isError: Boolean
) {
    var newTaskTitle by remember { mutableStateOf("") }
    var isAddingTask by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Доска проекта: ${project.title}",
                style = MaterialTheme.typography.h5
            )
            Button(
                onClick = onBack,
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
            ) {
                Text("Назад")
            }
        }

        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("ID доски: ${board.id}")
                Text("ID проекта: ${board.project}")
                Text("Задачи:", style = MaterialTheme.typography.h6)

                OutlinedTextField(
                    value = newTaskTitle,
                    onValueChange = { newTaskTitle = it },
                    label = { Text("Название задачи") },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        if (newTaskTitle.isBlank()) return@Button
                        isAddingTask = true
                        onAddTask(newTaskTitle)
                        newTaskTitle = ""
                        isAddingTask = false
                    },
                    enabled = !isAddingTask,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (isAddingTask) {
                        CircularProgressIndicator(Modifier.size(20.dp))
                    } else {
                        Text("Добавить задачу")
                    }
                }

                if (board.tasks.isEmpty()) {
                    Text("Нет задач", color = Color.Gray)
                } else {
                    LazyColumn {
                        items(board.tasks) { task ->
                            TaskCard(task)
                        }
                    }
                }
            }
        }

        if (message != null) {
            Text(
                text = message,
                color = if (isError) MaterialTheme.colors.error else MaterialTheme.colors.primary,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
private fun TaskCard(task: Task) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                task.title,
                style = MaterialTheme.typography.body1
            )
            Text(
                "Описание: ${task.description}",
                style = MaterialTheme.typography.body2
            )
            Text(
                "Назначено: ${task.assigned_to.username}",
                style = MaterialTheme.typography.body2
            )
        }
    }
}