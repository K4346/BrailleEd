package com.example.brailleed.ui.screens.alphabet_screen

import android.app.Application
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.AndroidViewModel
import com.example.brailleed.ui.composable.brailleRepository

class AlphabetViewModel(application: Application) :AndroidViewModel(application) {

    val selectedLanguageId = mutableIntStateOf(brailleRepository.getCurrentDict().langResId)
    fun getCurrentDict(resId:Int) = brailleRepository.getAlphabet(resId)

}