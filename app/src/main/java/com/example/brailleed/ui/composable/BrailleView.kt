package com.example.brailleed.ui.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.brailleed.domain.repositories.BrailleRepository
import com.example.brailleed.domain.use_cases.TrainerUseCase

//todo ?????
val brailleRepository: BrailleRepository = TrainerUseCase().brailleRepository

@Composable
fun BrailleView(dictId: Int? = null, letter: Char, sizeView: Int = 200) {
//    если dictId=null то берем из текущего словаря
    val points = if (dictId == null) brailleRepository.getBrailleChar(letter) else
        brailleRepository.getBrailleChar(dictId, letter)

    Canvas(modifier = Modifier.size(sizeView.dp)) {
        val radius = size.width / 8f
        val diameter = radius * 2
        val centerY = 0f
        val startX = 0f
        //todo пересмотреть
        val gap = size.width / 10
        val padding = -(startX + diameter + gap) / 2

        // Левая верхняя точка
        drawCircle(
            center = Offset(startX + radius - padding, centerY + radius),
            radius = radius,
            color = if (points[0]) Color.Black else Color.White,

            )
        // Правая верхняя точка
        drawCircle(
//            2*r = d
            center = Offset(startX + diameter + radius + gap - padding, centerY + radius),
            radius = radius,
            color = if (points[1]) Color.Black else Color.White,
        )

        // Средняя левая точка
        drawCircle(
            center = Offset(startX + radius - padding, centerY + radius + diameter + gap),
            radius = radius,
            color = if (points[2]) Color.Black else Color.White,
        )
        // Средняя правая точка
        drawCircle(
            center = Offset(
                startX + diameter + radius + gap - padding,
                centerY + radius + diameter + gap
            ),
            radius = radius,
            color = if (points[3]) Color.Black else Color.White,
        )

        // Нижняя левая точка
        drawCircle(
            center = Offset(startX + radius - padding, centerY + radius + 2 * diameter + 2 * gap),
            radius = radius,
            color = if (points[4]) Color.Black else Color.White,
        )
        // Нижняя правая точка
        drawCircle(
            center = Offset(
                startX + diameter + radius + gap - padding,
                centerY + radius + 2 * diameter + 2 * gap
            ),
            radius = radius,
            color = if (points[5]) Color.Black else Color.White,
        )
    }
}
