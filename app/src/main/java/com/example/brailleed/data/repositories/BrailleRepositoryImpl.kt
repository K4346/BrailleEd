package com.example.brailleed.data.repositories

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.StringRes
import androidx.compose.ui.text.intl.Locale
import com.example.brailleed.R
import com.example.brailleed.domain.repositories.BrailleRepository


object BrailleRepositoryImpl : BrailleRepository {

    private val brailleEnDict = mapOf(
        'a' to listOf(true, false, false, false, false, false),
        'b' to listOf(true, false, true, false, false, false),
        'c' to listOf(true, true, false, false, false, false),
        'd' to listOf(true, true, false, true, false, false),
        'e' to listOf(true, false, false, true, false, false),
        'f' to listOf(true, true, true, false, false, false),
        'g' to listOf(true, true, true, true, false, false),
        'h' to listOf(true, false, true, true, false, false),
        'i' to listOf(false, true, true, false, false, false),
        'j' to listOf(false, true, true, true, false, false),
        'k' to listOf(true, false, false, false, true, false),
        'l' to listOf(true, false, true, false, true, false),
        'm' to listOf(true, true, false, false, true, false),
        'n' to listOf(true, true, false, true, true, false),
        'o' to listOf(true, false, false, true, true, false),
        'p' to listOf(true, true, true, false, true, false),
        'q' to listOf(true, true, true, true, true, false),
        'r' to listOf(true, false, true, true, true, false),
        's' to listOf(false, true, true, false, true, false),
        't' to listOf(false, true, true, true, true, false),
        'u' to listOf(true, false, false, false, true, true),
        'v' to listOf(true, false, true, false, true, true),
        'w' to listOf(false, true, true, true, false, true),
        'x' to listOf(true, true, false, false, true, true),
        'y' to listOf(true, true, false, true, true, true),
        'z' to listOf(true, false, false, true, true, true)
    )
    private val brailleRuDict = mapOf(
        'а' to listOf(true, false, false, false, false, false),
        'б' to listOf(true, false, true, false, false, false),
        'в' to listOf(false, true, true, true, false, true),
        'г' to listOf(true, true, true, true, false, false),
        'д' to listOf(true, true, false, true, false, false),
        'е' to listOf(true, false, false, true, false, false),
        'ё' to listOf(true, false, false, false, false, true),
        'ж' to listOf(false, true, true, true, false, false),
        'з' to listOf(true, false, false, true, true, true),
        'и' to listOf(false, true, true, false, false, false),
        'й' to listOf(true, true, true, false, true, true),
        'к' to listOf(true, false, false, false, true, false),
        'л' to listOf(true, false, true, false, true, false),
        'м' to listOf(true, true, false, false, true, false),
        'н' to listOf(true, true, false, true, true, false),
        'о' to listOf(true, false, false, true, true, false),
        'п' to listOf(true, true, true, false, true, false),
        'р' to listOf(true, false, true, true, true, false),
        'с' to listOf(false, true, true, false, true, false),
        'т' to listOf(false, true, true, true, true, false),
        'у' to listOf(true, false, false, false, true, true),
        'ф' to listOf(true, true, true, false, false, false),
        'х' to listOf(true, false, true, true, false, false),
        'ц' to listOf(true, true, false, false, false, false),
        'ч' to listOf(true, true, true, true, true, false),
        'ш' to listOf(true, false, false, true, false, true),
        'щ' to listOf(true, true, false, false, true, true),
        'ъ' to listOf(true, false, true, true, true, true),
        'ы' to listOf(false, true, true, false, true, true),
        'ь' to listOf(false, true, true, true, true, true),
        'э' to listOf(false, true, true, false, false, true),
        'ю' to listOf(true, false, true, true, false, true),
        'я' to listOf(true, true, true, false, false, true)
    )
    private val brailleNumbersDict = mapOf(
        '1' to listOf(true, false, false, false, false, false),
        '2' to listOf(true, false, true, false, false, false),
        '3' to listOf(true, true, false, false, false, false),
        '4' to listOf(true, true, false, true, false, false),
        '5' to listOf(true, false, false, true, false, false),
        '6' to listOf(true, true, true, false, false, false),
        '7' to listOf(true, true, true, true, false, false),
        '8' to listOf(true, false, true, true, false, false),
        '9' to listOf(false, true, true, false, false, false),
        '0' to listOf(false, true, true, true, false, false),
        '.' to listOf(false, false, true, true, false, true),
        ',' to listOf(false, false, true, false, false, false),
        '?' to listOf(false, false, true, false, false, true),
        ';' to listOf(false, false, true, false, true, false),
        '!' to listOf(false, false, true, true, true, false),
        '«' to listOf(false, false, true, false, true, true),
        '»' to listOf(false, false, false, true, true, true),
        '-' to listOf(false, false, false, false, true, true)
    )
    private val brailleDict = mapOf(
        Dicts.EN.name to brailleEnDict,
        Dicts.RU.name to brailleRuDict,
        Dicts.NM.name to brailleNumbersDict,
    )

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
        if (brailleDict.keys.contains(phoneLocale)) return phoneLocale
        else return Dicts.RU.name
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
}