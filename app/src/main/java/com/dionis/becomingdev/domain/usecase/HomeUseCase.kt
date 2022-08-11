package com.dionis.becomingdev.domain.usecase

import com.dionis.becomingdev.domain.model.Members
import com.dionis.becomingdev.infrastructure.interfaces.usecase.IHomeUseCase
import com.dionis.becomingdev.infrastructure.interfaces.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//interface IHomeUseCase {
//    suspend fun getMembers(): Flow<MembersItem>
//}

class HomeUseCase @Inject constructor(private val homeRepository: HomeRepository): IHomeUseCase {

    override suspend fun getMembers(): Flow<Members> {
        return homeRepository.getMembers()

    }
}