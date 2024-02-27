package com.example.brailleed.ui.screens.alphabet_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.brailleed.ui.composable.BrailleView
import com.example.brailleed.ui.composable.LanguageSelection
import com.example.brailleed.ui.theme.fontRobotoFamily

@Composable
fun AlphabetScreen(
    vm: AlphabetViewModel = viewModel(),
    navHostController: NavHostController
) {
    val selectedLanguageId = remember { vm.selectedLanguageId } // Язык шрифта Брайля

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))
        // Выбор языка шрифта Брайля
        LanguageSelection(selectedLanguage = selectedLanguageId)

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Adaptive(100.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            vm.getCurrentDict(selectedLanguageId.intValue).forEachIndexed { i, char ->
                item {
                    Card() {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            BrailleView(selectedLanguageId.intValue, letter = char, 100)
//                            todo добавить стили - шрифт и размер
                            Text(
                                text = char.toString(),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = fontRobotoFamily
                            )
                        }

                    }
                }
            }
        }

    }
}