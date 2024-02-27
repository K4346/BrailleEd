package com.example.brailleed.domain.use_cases

import com.example.brailleed.data.repositories.BrailleRepositoryImpl
import com.example.brailleed.domain.repositories.BrailleRepository

class TrainerUseCase {
    val brailleRepository: BrailleRepository = BrailleRepositoryImpl

    fun getRandomChars(count: Int): ArrayList<TrainerChoosingEntity> {
        val randomChars = arrayListOf<TrainerChoosingEntity>()
        (1..count).forEach {
            randomChars.add(getRandomChoosingChars())
        }
        return randomChars
    }
    fun getRandomChoosingChars(): TrainerChoosingEntity {
            var entity = initTrainerChoosingEntity()
            while (entity.wrongs.any { c -> c == entity.right }) {
                entity = initTrainerChoosingEntity()
            }

        return entity
    }

    //todo обязательно оптимизировать !!!!!! так как райт не записывается в общее
    private fun initTrainerChoosingEntity(): TrainerChoosingEntity {
        return  with(brailleRepository){
            TrainerChoosingEntity(
                right = getRandomChar(),
                wrongs = listOf(getRandomChar(),getRandomChar(),getRandomChar())
            )
        }
    }
}

data class TrainerChoosingEntity(
    val right: Char,
    val wrongs: List<Char>
)