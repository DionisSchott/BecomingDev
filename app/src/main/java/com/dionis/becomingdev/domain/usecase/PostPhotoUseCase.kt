package com.dionis.becomingdev.domain.usecase

import com.dionis.becomingdev.infrastructure.interfaces.repository.PostPhotoRepository
import com.dionis.becomingdev.model.photos.PostPhotoBody
import com.dionis.becomingdev.model.photos.PostPhotoResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostPhotoUseCase @Inject constructor(private val postPhotoRepository: PostPhotoRepository) {

    suspend fun postPhoto(body: PostPhotoBody): Flow<PostPhotoResponse> {
        return postPhotoRepository.postPhoto(body)
    }
}