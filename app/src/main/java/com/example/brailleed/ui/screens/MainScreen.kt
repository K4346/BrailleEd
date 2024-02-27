package com.example.brailleed.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.brailleed.BrailleEdNavigation
import com.example.brailleed.BrailleEdRoutes
import com.example.brailleed.R
import com.example.brailleed.data.repositories.Dicts
import com.example.brailleed.ui.composable.LanguageSelection
import com.example.brailleed.ui.composable.brailleRepository

@Composable
fun MainScreen(
    //vm: BrailleTrainerViewModel = viewModel(),
    navHostController: NavHostController
) {

//    todo возможно убрать в вьюмодель

    val selectedLanguage =
        remember { mutableStateOf(brailleRepository.getCurrentDict().langResId) } // Язык шрифта Брайля
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Выбор языка шрифта Брайля
        LanguageSelection(selectedLanguage = selectedLanguage) {
//            todo возможно не надо, так как и так можно в код перенести и будет срабатывать при рекомпиляции
            brailleRepository.changeCurrentDict(selectedLanguage.value)
        }

        Spacer(modifier = Modifier.height(32.dp))
        // Кнопка для перехода на экран тренажера
        Button(
            onClick = {
                navHostController.navigate(BrailleEdRoutes.BrailleTrainerScreen.name)
            }
        ) {
            Text(text = stringResource(R.string.Braille_simulator))
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Кнопка для перехода на экран алфавита
        Button(
            onClick = {
                navHostController.navigate(BrailleEdRoutes.AlphabetScreen.name)
            }
        ) {
            Text(text = stringResource(R.string.BrailleAlphabet))
        }
    }
}
