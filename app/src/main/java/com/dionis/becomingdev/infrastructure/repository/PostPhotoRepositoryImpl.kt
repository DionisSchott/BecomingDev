package com.dionis.becomingdev.infrastructure.repository

import com.dionis.becomingdev.data.api.photo.PostPhotoApi
import com.dionis.becomingdev.infrastructure.interfaces.repository.PostPhotoRepository
import com.dionis.becomingdev.model.photos.PostPhotoBody
import com.dionis.becomingdev.model.photos.PostPhotoResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostPhotoRepositoryImpl @Inject constructor(private val postPhotoApi: PostPhotoApi) : PostPhotoRepository {

    override suspend fun postPhoto(body: PostPhotoBody): Flow<PostPhotoResponse> = flow {
        emit(postPhotoApi.postPhoto(body))
    }
}