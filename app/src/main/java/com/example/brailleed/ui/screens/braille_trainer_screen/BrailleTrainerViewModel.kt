package com.example.brailleed.ui.screens.braille_trainer_screen

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel

class BrailleTrainerViewModel(application: Application) :AndroidViewModel(application) {
    val currentChar=  mutableStateOf(trainerUseCase.getRandomChoosingChars())
    var rightCount = 0
    var allCount = 0

    var choosingOptions = initChoosingOptions()

    var selectedChar:MutableState<Char?> = mutableStateOf(null)

    var answered =mutableStateOf(false)

    fun nextChoosing(char: Char?){
        if (char==null) error("Невозможно попасть, но на всякий пока выброшу исключение")
        allCount+=1
        if (char==currentChar.value.right){
            rightCount+=1
        }
        changeChar()
    }

    private fun changeChar(){
        currentChar.value= trainerUseCase.getRandomChoosingChars()
        choosingOptions = initChoosingOptions()
    }
    private fun initChoosingOptions(): MutableList<Char> {
        val l = currentChar.value.wrongs.toMutableList()
        l.add(currentChar.value.right)
        l.shuffle()
        return l
    }
}