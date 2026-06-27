package edu.ucne.dragonballplanetapi.domain.character.repository

import edu.ucne.dragonballplanetapi.data.remote.Resource
import edu.ucne.dragonballplanetapi.domain.character.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(
        page: Int,
        limit: Int,
        name: String?,
        gender: String?,
        race: String?,
    ): Flow<Resource<List<Character>>>

    fun getCharacterDetail(id: Int): Flow<Resource<Character>>
}