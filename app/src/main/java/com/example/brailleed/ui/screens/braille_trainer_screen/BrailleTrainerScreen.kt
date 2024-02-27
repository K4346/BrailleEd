package com.example.brailleed.ui.screens.braille_trainer_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.brailleed.R
import com.example.brailleed.domain.use_cases.TrainerUseCase
import com.example.brailleed.ui.composable.BrailleView
import com.example.brailleed.ui.composable.ChooseCharButton

val trainerUseCase = TrainerUseCase()

//todo сделать скролл
@Composable
fun BrailleTrainerScreen(
    vm: BrailleTrainerViewModel = viewModel(),
    navHostController: NavHostController
) {

    val currentChar by remember { vm.currentChar }

    var selectedChar by remember { vm.selectedChar}

    var answered by remember { vm.answered }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "${vm.rightCount}/${vm.allCount}",
            Modifier
                .padding(8.dp)
                .align(Alignment.End)
        )

        BrailleView(letter = currentChar.right)
        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            vm.choosingOptions.forEach { char ->
                item {
                    ChooseCharButton(
                        onClick = {
                            answered = true
                            selectedChar = char
                        },
                        char = char,
                        rightAnswer = currentChar.right,
                        answered = answered
                    )
                }
            }
        }
        Button(
            enabled = answered,
            onClick = {
                answered = false
                vm.nextChoosing(selectedChar)
            },
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(text = stringResource(R.string.next))
        }

        Button(
            onClick = {
                navHostController.popBackStack()
            },
            modifier = Modifier
                .padding(20.dp)
        ) {
            Text(text = stringResource(R.string.finish))
        }
    }
}