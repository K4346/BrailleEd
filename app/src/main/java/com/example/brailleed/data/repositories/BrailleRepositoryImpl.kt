package com.example.brailleed.data.repositories

import androidx.annotation.StringRes
import com.example.brailleed.R
import com.example.brailleed.domain.repositories.BrailleRepository


object BrailleRepositoryImpl : BrailleRepository {
    //    todo переделать, добавить русский (проверить)
    private val brailleEnDict = mapOf(
        'a' to listOf(true, false, false, false, false, false),
        'b' to listOf(true, true, false, false, false, false),
        'c' to listOf(true, false, false, true, false, false),
        'd' to listOf(true, false, false, true, true, false),
        'e' to listOf(true, false, false, false, true, false),
        'f' to listOf(true, true, false, true, false, false),
        'g' to listOf(true, true, false, true, true, false),
        'h' to listOf(true, true, false, false, true, false),
        'i' to listOf(false, true, false, true, false, false),
        'j' to listOf(false, true, false, true, true, false),
        'k' to listOf(true, false, true, false, false, false),
        'l' to listOf(true, true, true, false, false, false),
        'm' to listOf(true, false, true, true, false, false),
        'n' to listOf(true, false, true, true, true, false),
        'o' to listOf(true, false, true, false, true, false),
        'p' to listOf(true, true, true, true, false, false),
        'q' to listOf(true, true, true, true, true, false),
        'r' to listOf(true, true, true, false, true, false),
        's' to listOf(false, true, true, true, false, false),
        't' to listOf(false, true, true, true, true, false),
        'u' to listOf(true, false, true, false, false, true),
        'v' to listOf(true, true, true, false, false, true),
        'w' to listOf(false, true, false, true, true, true),
        'x' to listOf(true, false, true, true, false, true),
        'y' to listOf(true, false, true, true, true, true),
        'z' to listOf(true, false, true, false, true, true)
    )
    private val brailleRuDict = mapOf(
        'a' to listOf(true, false, false, false, false, false),
        'b' to listOf(true, true, false, false, false, false),
        'c' to listOf(true, false, false, true, false, false),
        'd' to listOf(true, false, false, true, true, false),
        'e' to listOf(true, false, false, false, true, false),
        'f' to listOf(true, true, false, true, false, false),
        'g' to listOf(true, true, false, true, true, false),
        'h' to listOf(true, true, false, false, true, false),
        'i' to listOf(false, true, false, true, false, false),
        'j' to listOf(false, true, false, true, true, false),
        'k' to listOf(true, false, true, false, false, false),
        'l' to listOf(true, true, true, false, false, false),
        'm' to listOf(true, false, true, true, false, false),
        'n' to listOf(true, false, true, true, true, false),
        'o' to listOf(true, false, true, false, true, false),
        'p' to listOf(true, true, true, true, false, false),
        'q' to listOf(true, true, true, true, true, false),
        'r' to listOf(true, true, true, false, true, false),
        's' to listOf(false, true, true, true, false, false),
        't' to listOf(false, true, true, true, true, false),
        'u' to listOf(true, false, true, false, false, true),
        'v' to listOf(true, true, true, false, false, true),
        'w' to listOf(false, true, false, true, true, true),
        'x' to listOf(true, false, true, true, false, true),
        'y' to listOf(true, false, true, true, true, true),
        'z' to listOf(true, false, true, false, true, true)
    )
    private val brailleNumbersDict = mapOf(
        '1' to listOf(true, false, false, false, false, false),
        '2' to listOf(true, true, false, false, false, false),
        '3' to listOf(true, false, false, true, false, false),
        '4' to listOf(true, false, false, true, true, false),
        '5' to listOf(true, false, false, false, true, false),
        '6' to listOf(true, true, false, true, false, false),
        '7' to listOf(true, true, false, true, true, false),
        '8' to listOf(true, true, false, false, true, false),
        '9' to listOf(false, true, false, true, false, false),
        '0' to listOf(false, true, false, true, true, false),
        'k' to listOf(true, false, true, false, false, false),
        'l' to listOf(true, true, true, false, false, false),
        'm' to listOf(true, false, true, true, false, false),
        'n' to listOf(true, false, true, true, true, false),
        'o' to listOf(true, false, true, false, true, false),
        'p' to listOf(true, true, true, true, false, false),
        'q' to listOf(true, true, true, true, true, false),
        'r' to listOf(true, true, true, false, true, false),
        's' to listOf(false, true, true, true, false, false),
        't' to listOf(false, true, true, true, true, false),
        'u' to listOf(true, false, true, false, false, true),
        'v' to listOf(true, true, true, false, false, true),
        'w' to listOf(false, true, false, true, true, true),
        'x' to listOf(true, false, true, true, false, true),
        'y' to listOf(true, false, true, true, true, true),
        'z' to listOf(true, false, true, false, true, true)
    )
    private val brailleDict = mapOf(
        Dicts.EN to brailleEnDict,
        Dicts.RU to brailleRuDict,
        Dicts.NM to brailleNumbersDict,
    )
//    todo передалть, мб добавить sharedpref и или язык по умолчанию
    private var currentDict:Dicts = Dicts.RU

    override fun getCurrentDict() = currentDict

    override fun changeCurrentDict(@StringRes langResId: Int) {
       currentDict = Dicts.values().find { it.langResId == langResId }?:Dicts.EN
    }

    override fun getBrailleChar(char: Char): List<Boolean> {
        return brailleDict[currentDict]?.get(char) ?: error("Character not supported: $char")
    }

    override fun getBrailleChar(dictId:Int, char: Char): List<Boolean> {
        val dict =  Dicts.values().find { it.langResId == dictId }?:Dicts.EN
        return brailleDict[dict]?.get(char) ?: error("Character not supported: $char")
    }

    override fun getRandomChar(): Char {
        val randomEntry = brailleDict[currentDict]!!.keys.random()
        return randomEntry
    }

//    todo можно оптимизировать получая сразу все, но нужно ли
    override fun getAlphabet(resId: Int): Set<Char> {
    val dict =  Dicts.values().find { it.langResId == resId }?:Dicts.EN
        return brailleDict[dict]!!.keys
    }
}

enum class Dicts(@StringRes val langResId: Int) {
    RU(R.string.Russian),
    EN(R.string.English),
    NM(R.string.Numbers_and_signs)
}