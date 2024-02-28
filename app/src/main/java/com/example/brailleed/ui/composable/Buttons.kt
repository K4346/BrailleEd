package com.example.brailleed.ui.composable


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brailleed.ui.theme.RightChoose
import com.example.brailleed.ui.theme.WrongChoose
import com.example.brailleed.ui.theme.fontRobotoFamily

@Composable
fun ChooseCharButton(
    onClick: () -> Unit,
    char: Char,
    rightAnswer: Char,
    answered: Boolean,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {
            onClick()
        },
        modifier = modifier
            .padding(8.dp),
        colors =
        if (!answered) ButtonDefaults.buttonColors() else
            if (char == rightAnswer)
                ButtonDefaults.buttonColors(
                    disabledContainerColor = RightChoose
                ) else
                ButtonDefaults.buttonColors(
                    disabledContainerColor = WrongChoose
                ),
        enabled = !answered
    ) {

        Text(
            text = char.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = fontRobotoFamily
        )
    }
}