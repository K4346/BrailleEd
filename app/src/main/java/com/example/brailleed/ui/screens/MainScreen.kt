package com.example.brailleed.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.brailleed.BrailleEdRoutes
import com.example.brailleed.R
import com.example.brailleed.ui.composable.LanguageSelection
import com.example.brailleed.ui.composable.brailleRepository
import com.example.brailleed.ui.theme.fontRobotoFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navHostController: NavHostController
) {

    val selectedLanguage =
        remember { mutableStateOf(brailleRepository.getCurrentDict().langResId) } // Язык шрифта Брайля
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Выбор языка шрифта Брайля
            LanguageSelection(selectedLanguage = selectedLanguage) {
//            todo возможно не надо, так как и так можно в код перенести и будет срабатывать при рекомпиляции
                brailleRepository.changeCurrentDict(selectedLanguage.value)
            }

            Spacer(modifier = Modifier.height(8.dp))
            // Кнопка для перехода на экран тренажера
            Button(
                onClick = {
                    navHostController.navigate(BrailleEdRoutes.BrailleTrainerScreen.name)
                }
            ) {

                Text(
                    text = stringResource(R.string.Braille_simulator),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = fontRobotoFamily
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            // Кнопка для перехода на экран алфавита
            Button(
                onClick = {
                    navHostController.navigate(BrailleEdRoutes.AlphabetScreen.name)
                }
            ) {
                Text(
                    text = stringResource(R.string.BrailleAlphabet),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = fontRobotoFamily
                )
            }
        }
    }
}
