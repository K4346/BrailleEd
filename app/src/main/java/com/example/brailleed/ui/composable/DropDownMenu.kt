package com.example.brailleed.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.brailleed.R
import com.example.brailleed.data.repositories.Dicts
import com.example.brailleed.ui.theme.fontRobotoFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageSelection(selectedLanguage: MutableState<Int>, onclick: () -> Unit = {}) {
    val languages = Dicts.values()
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = stringResource(
                    R.string.selected_language,
                    stringResource(id = selectedLanguage.value)
                ),
                textStyle = LocalTextStyle.current.copy(
                    fontFamily = fontRobotoFamily,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.menuAnchor(),
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                languages.forEach { language ->
                    val langName = stringResource(id = language.langResId)
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = langName,
                                fontWeight = FontWeight.Light,
                                fontFamily = fontRobotoFamily
                            )
                        },
                        onClick = {
                            selectedLanguage.value = language.langResId
                            expanded = false
                            onclick()
                        }
                    )
                }
            }
        }
    }
}