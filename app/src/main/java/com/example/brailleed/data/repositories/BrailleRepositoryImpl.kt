package com.example.brailleed.data.repositories

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.StringRes
import androidx.compose.ui.text.intl.Locale
import com.example.brailleed.R
import com.example.brailleed.data.BrailleDictionaries
import com.example.brailleed.domain.repositories.BrailleRepository


object BrailleRepositoryImpl : BrailleRepository {


    private val brailleDict = BrailleDictionaries().brailleDict

    private var streamOfChars = mutableListOf<Char>()

    private lateinit var sharedPreferences: SharedPreferences
    fun initSharedPreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences(BRAILLE_ED_STORAGE, Context.MODE_PRIVATE)
    }

    private var currentDictCodeName: String
        get() = sharedPreferences.getString(DEFAULT_LANG_DICT, defaultLangDict())!!
        set(langDict) {
            sharedPreferences.edit().putString(DEFAULT_LANG_DICT, langDict).apply()
        }

    private fun defaultLangDict(): String {
        val phoneLocale = Locale.current.language.uppercase()
        return if (brailleDict.keys.contains(phoneLocale)) phoneLocale
        else Dicts.RU.name
    }

    override fun getCurrentDict() = Dicts.valueOf(currentDictCodeName)

    override fun changeCurrentDict(@StringRes langResId: Int) {
        currentDictCodeName =
            Dicts.values().find { it.langResId == langResId }?.name ?: Dicts.RU.name
        initStreamOfChars()
    }

    override fun getBrailleChar(char: Char): List<Boolean> {
        return brailleDict[currentDictCodeName]?.get(char)
            ?: error("Character not supported: $char")
    }

    override fun getBrailleChar(dictId: Int, char: Char): List<Boolean> {
        val dict = Dicts.values().find { it.langResId == dictId }?.name ?: Dicts.RU.name
        return brailleDict[dict]?.get(char) ?: error("Character not supported: $char")
    }

    private fun initStreamOfChars() {
        val count = brailleDict[currentDictCodeName]!!.count()
        val stream = brailleDict[currentDictCodeName]!!.keys.toMutableList()

        repeat(count * (1.7).toInt()) {
            stream.add(getRandomChar())
        }
        streamOfChars = stream
    }

    override fun getRightChar(): Char {
        if (streamOfChars.isEmpty()) {
            initStreamOfChars()
        }

        val char = streamOfChars.last()
        streamOfChars.removeLast()
        return char
    }

    override fun getWrongChars(right: Char): List<Char> {
        val count = brailleDict[currentDictCodeName]!!.count()
        val wrongs = mutableListOf<Char>()
        val chars = brailleDict[currentDictCodeName]!!.keys.toMutableList()
        while (wrongs.size != WRONG_ANSWERS_COUNT) {
            val i = (0 until count).random()
            if (!wrongs.contains(chars[i]) && chars[i] != right) {
                wrongs.add(chars[i])
            }
        }
        return wrongs
    }

    private fun getRandomChar(): Char {
        val randomEntry = brailleDict[currentDictCodeName]!!.keys.random()
        return randomEntry
    }

    //    todo можно оптимизировать получая сразу все, но нужно ли
    override fun getAlphabet(resId: Int): Set<Char> {
        val dict = Dicts.values().find { it.langResId == resId }?.name ?: Dicts.RU.name
        return brailleDict[dict]!!.keys
    }

    private const val WRONG_ANSWERS_COUNT = 3
    private const val DEFAULT_LANG_DICT = "DEFAULT_LANG_DICT"
    private const val BRAILLE_ED_STORAGE = "BRAILLE_ED_STORAGE"
}


enum class Dicts(@StringRes val langResId: Int) {
    NM(R.string.Numbers_and_signs),
    EN(R.string.English),
    RU(R.string.Russian),
    ES(R.string.Spanish),
}