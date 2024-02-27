package com.example.brailleed.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.brailleed.data.repositories.Dicts


@Composable
fun LanguageSelection(selectedLanguage: MutableState<Int>,onclick:()->Unit={}) {
    var expanded by remember { mutableStateOf(true) }
    val languages = Dicts.values() // Список поддерживаемых языков
    Column {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded =false},
            modifier = Modifier.width(200.dp)
        ) {
            languages.forEach { language ->
                val langName =  stringResource(id = language.langResId)
                DropdownMenuItem(
                    text = { Text(text = langName ) },
                    onClick = {
                        selectedLanguage.value =  language.langResId
                        expanded = false
                        onclick()
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Selected Language: ${stringResource(id = selectedLanguage.value)}",modifier = Modifier
            .padding(10.dp)
            .clickable(onClick = { expanded = true }))
    }
}