package com.dionis.auladokevyn.domain.usecase

import com.dionis.auladokevyn.domain.model.Members
import com.dionis.auladokevyn.infrastructure.interfaces.usecase.IHomeUseCase
import com.dionis.auladokevyn.infrastructure.interfaces.repository.HomeRepository
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