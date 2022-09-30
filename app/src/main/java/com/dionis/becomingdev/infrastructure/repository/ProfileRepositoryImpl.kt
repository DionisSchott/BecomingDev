package com.dionis.becomingdev.infrastructure.repository

import com.dionis.becomingdev.data.api.profile.ProfileApi
import com.dionis.becomingdev.domain.model.MembersItem
import com.dionis.becomingdev.infrastructure.interfaces.repository.ProfileRepository
import com.dionis.becomingdev.model.editMember.EditUserBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val profileApi: ProfileApi):
    ProfileRepository {

    override suspend fun editUser(memberId: Int, body: EditUserBody): Flow<MembersItem> = flow{
        emit(profileApi.editUser(memberId, body))

    }
}