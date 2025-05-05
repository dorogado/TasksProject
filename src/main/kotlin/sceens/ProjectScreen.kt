package screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog


@Composable
fun Card(text: String, x: Dp, y: Dp, onClick: (String) -> Unit) {
    Box(modifier = Modifier.offset(x, y).clickable {onClick(text)}) {
        Image(
            painter = painterResource("img.png"),
            contentDescription = null
        )
        Text(
            text = text, fontSize = 20.sp, modifier = Modifier.padding(10.dp).align(Alignment.Center)
        )
    }
}


@Composable
fun AddCardDialog(onDismissRequest: () -> Unit, onClickAddButton: (String) -> Unit) {
    var textFieldValue by remember { mutableStateOf("") }
    Dialog(onCloseRequest = onDismissRequest) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(value = textFieldValue, onValueChange = { textFieldValue = it })
            Button(onClick = { onClickAddButton(textFieldValue) }) {
                Text("Добавить")
            }
        }
    }
}


@Composable
fun ProjectScreen(goToProjectsScreen: () -> Unit) {

    val cardsList = remember { mutableStateListOf<String>() }
    val inProcessCardsList = remember { mutableStateListOf<String>() }
    val completedCardsList = remember { mutableStateListOf<String>() }

    var onDismissRequest by remember { mutableStateOf(false) }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onDismissRequest = true }) {
                Icon(Icons.Default.Add, null)
            }
        },
        topBar = { Text(text = "Проекты", modifier = Modifier.clickable { goToProjectsScreen() }) }
    ) {

        if (onDismissRequest) {
            AddCardDialog(onDismissRequest = {onDismissRequest = false}, onClickAddButton = { text ->
                cardsList.add(text)
                onDismissRequest = false
            })
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Green)
                .border(BorderStroke(15.dp, Color.Yellow))
        ) {
            Row() {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(10f)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = "Выполнены",
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 30.sp
                    )

                    for (card in completedCardsList) {
                        Card(card, (0..100).random().dp, (0..200).random().dp) { task ->
                            completedCardsList.remove(task)
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White)
                        .height(500.dp)
                        .align(Alignment.CenterVertically)
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(10f)
                ) {
                    Text(
                        text = "В процессе",
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 30.sp
                    )
                    for (card in inProcessCardsList) {
                        Card(card, (0..100).random().dp, (0..200).random().dp) { task ->
                            completedCardsList.add(task)
                            inProcessCardsList.remove(task)
                        }
                    }

                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White)
                        .height(500.dp)
                        .align(Alignment.CenterVertically)
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(10f)
                ) {
                    for (card in cardsList) {
                        Card(card, (0..100).random().dp, (0..200).random().dp) { task ->
                            inProcessCardsList.add(task)
                            cardsList.remove(task)
                        }
                    }
                    Text(
                        text = "Не начаты",
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 30.sp
                    )
                }

            }
        }
    }

}