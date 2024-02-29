package com.example.brailleed.ui.screens.braille_trainer_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.brailleed.R
import com.example.brailleed.domain.use_cases.TrainerUseCase
import com.example.brailleed.ui.composable.BrailleView
import com.example.brailleed.ui.composable.ChooseCharButton
import com.example.brailleed.ui.theme.fontRobotoFamily

val trainerUseCase = TrainerUseCase()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrailleTrainerScreen(
    vm: BrailleTrainerViewModel = viewModel(),
    navHostController: NavHostController
) {

    val currentChar by remember { vm.currentChar }

    var selectedChar by remember { vm.selectedChar }

    var answered by remember { vm.answered }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.Braille_simulator)) },
                navigationIcon =
                {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                })
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(contentPadding)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "${vm.rightCount}/${vm.allCount}",
                Modifier
                    .padding(8.dp)
                    .align(Alignment.End),
                fontWeight = FontWeight.Bold,
                fontFamily = fontRobotoFamily
            )

            BrailleView(letter = currentChar.right)
            Spacer(modifier = Modifier.height(16.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ChooseCharButton(
                    onClick = {
                        answered = true
                        selectedChar = vm.choosingOptions[0]
                    },
                    char = vm.choosingOptions[0],
                    rightAnswer = currentChar.right,
                    answered = answered,
                    modifier = Modifier.weight(1f)
                )
                ChooseCharButton(
                    onClick = {
                        answered = true
                        selectedChar = vm.choosingOptions[1]
                    },
                    char = vm.choosingOptions[1],
                    rightAnswer = currentChar.right,
                    answered = answered,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ChooseCharButton(
                    onClick = {
                        answered = true
                        selectedChar = vm.choosingOptions[2]
                    },
                    char = vm.choosingOptions[2],
                    rightAnswer = currentChar.right,
                    answered = answered,
                    modifier = Modifier.weight(1f)
                )
                ChooseCharButton(
                    onClick = {
                        answered = true
                        selectedChar = vm.choosingOptions[3]
                    },
                    char = vm.choosingOptions[3],
                    rightAnswer = currentChar.right,
                    answered = answered,
                    modifier = Modifier.weight(1f)
                )
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
                Text(
                    text = stringResource(R.string.next),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = fontRobotoFamily
                )
            }

            Button(
                onClick = {
                    navHostController.popBackStack()
                },
                modifier = Modifier
                    .padding(20.dp)
            ) {
                Text(
                    text = stringResource(R.string.finish),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = fontRobotoFamily
                )
            }
        }
    }
}