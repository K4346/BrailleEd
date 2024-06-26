package com.example.brailleed.data

import com.example.brailleed.data.repositories.Dicts

class BrailleDictionaries {

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

    private val brailleEsDict = mapOf(
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
        'z' to listOf(true, false, false, true, true, true),
        'á' to listOf(true, false, true, true, true, true),
        'é' to listOf(false, true, true, false, true, true),
        'ú' to listOf(false, true, true, true, true, true),
        'ñ' to listOf(true, true, true, true, false, true),
        'ü' to listOf(true, false, true, true, false, true),
        'í' to listOf(false, true, false, false, true, false),
        'ó' to listOf(false, true, false, false, true, true),
    )

    val brailleDict = mapOf(
        Dicts.EN.name to brailleEnDict,
        Dicts.RU.name to brailleRuDict,
        Dicts.NM.name to brailleNumbersDict,
        Dicts.ES.name to brailleEsDict,
    )
}