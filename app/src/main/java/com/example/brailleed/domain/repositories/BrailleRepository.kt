package com.example.brailleed.domain.repositories

import androidx.annotation.StringRes
import com.example.brailleed.data.repositories.Dicts

interface BrailleRepository {
    fun getBrailleChar(dictId: Int, char: Char): List<Boolean>

    fun getBrailleChar(char: Char): List<Boolean>

    fun getAlphabet(resId: Int): Set<Char>


    fun getRightChar(): Char

    fun getWrongChars(right: Char):List<Char>
    fun changeCurrentDict(@StringRes langResId: Int)

    fun getCurrentDict(): Dicts
}