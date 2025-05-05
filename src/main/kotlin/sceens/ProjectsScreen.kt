package screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.DrawerDefaults.shape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog


@Composable
fun ProjectsScreen(onGoToProject: () -> Unit, onSignOut: () -> Unit) {
    var onDismissRequest by remember { mutableStateOf(false) }
    val projects = remember { mutableStateListOf<String>() }

    if (onDismissRequest) {
        AddCardDialog(
            onDismissRequest = {onDismissRequest = false},
            onClickAddButton = {
                projects.add(it)
                onDismissRequest = false
            }
        )
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.padding(1.dp).border(BorderStroke(1.dp, Color.Black)).fillMaxWidth().padding(150.dp, 50.dp),
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                Box(modifier = Modifier.border(BorderStroke(1.dp, Color.Black)).padding(1.dp)) {
                    IconButton(
                        onClick = { onDismissRequest = true }
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                }

                Box(modifier = Modifier.border(BorderStroke(1.dp, Color.Black)).padding(1.dp)) {
                    IconButton(
                        onClick = { onSignOut() }
                    ) {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = null)
                    }
                }

            }
        }
    ) {
        Box(Modifier.fillMaxSize()) {
            LazyColumn {
                items(projects) { item ->
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .border(BorderStroke(1.dp, Color.Black))
                            .clickable { onGoToProject() }
                    ) {
                        Text(item)
                    }
                }
            }
        }
    }
}