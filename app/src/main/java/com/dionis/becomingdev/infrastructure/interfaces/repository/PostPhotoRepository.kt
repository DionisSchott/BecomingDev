package com.dionis.becomingdev.infrastructure.interfaces.repository

import com.dionis.becomingdev.model.photos.PostPhotoBody
import com.dionis.becomingdev.model.photos.PostPhotoResponse
import kotlinx.coroutines.flow.Flow

interface PostPhotoRepository {
    suspend fun postPhoto(body: PostPhotoBody): Flow<PostPhotoResponse>
}