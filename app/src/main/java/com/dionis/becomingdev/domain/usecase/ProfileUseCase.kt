package com.dionis.becomingdev.domain.usecase

import com.dionis.becomingdev.domain.model.MembersItem
import com.dionis.becomingdev.infrastructure.interfaces.repository.ProfileRepository
import com.dionis.becomingdev.infrastructure.interfaces.usecase.IProfileUseCase
import com.dionis.becomingdev.model.editMember.EditUserBody
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileUseCase @Inject constructor(private val profileRepository: ProfileRepository):
    IProfileUseCase {

    override suspend fun editUser(memberId: Int, body: EditUserBody): Flow<MembersItem> {
        return profileRepository.editUser(memberId, body)

    }
}