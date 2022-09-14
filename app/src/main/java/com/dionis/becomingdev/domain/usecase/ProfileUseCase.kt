package com.dionis.becomingdev.domain.usecase

import com.dionis.becomingdev.domain.model.MembersItem
import com.dionis.becomingdev.infrastructure.interfaces.repository.ProfileRepository
import com.dionis.becomingdev.infrastructure.interfaces.usecase.IProfileUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileUseCase @Inject constructor(private val profileRepository: ProfileRepository):
    IProfileUseCase {

    override suspend fun getUserInfo(memberId: Int): Flow<MembersItem> {
        return profileRepository.getUserInfo(memberId)

    }
}