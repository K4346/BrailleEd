package com.example.brailleed.domain.use_cases

import com.example.brailleed.data.repositories.BrailleRepositoryImpl
import com.example.brailleed.domain.repositories.BrailleRepository

class TrainerUseCase {
    val brailleRepository: BrailleRepository = BrailleRepositoryImpl

    fun getRandomChoosingChars(): TrainerChoosingEntity {
        var entity = initTrainerChoosingEntity()
        while (entity.wrongs.any { c -> c == entity.right }) {
            entity = initTrainerChoosingEntity()
        }

        return entity
    }

    private fun initTrainerChoosingEntity(): TrainerChoosingEntity {
        return with(brailleRepository) {
            val right = getRightChar()
            TrainerChoosingEntity(
                right = right,
                wrongs = getWrongChars(right)
            )
        }
    }
}

data class TrainerChoosingEntity(
    val right: Char,
    val wrongs: List<Char>
)