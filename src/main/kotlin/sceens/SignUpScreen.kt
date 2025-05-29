package screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SignInScreen(onGoToSignUpScreen: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(value = "", onValueChange = {})
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(value = "", onValueChange = {})
        Spacer(Modifier.height(10.dp))
        Text(text  = "Зарегистрироваться", color = Color.Blue, modifier = Modifier.clickable {onGoToSignUpScreen()})
        Spacer(Modifier.height(10.dp))
        Button(onClick = {}) {
            Text("войти")
        }
    }
}
// кирилл написал больше сани