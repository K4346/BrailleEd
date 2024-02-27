package com.example.brailleed.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.brailleed.ui.composable.BrailleView


//todo удалить
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen() {
    var text by remember { mutableStateOf("g") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Введите текст для отображения в брайле") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        BrailleText(text = text)
    }
}

@Composable
fun BrailleText(text: String) {
    Column {
        for (char in text) {
            BrailleView(letter = char)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBrailleApp() {
    TestScreen()
}
