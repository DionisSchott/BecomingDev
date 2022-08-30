package com.dionis.becomingdev.data.api.photo

import com.dionis.becomingdev.model.photos.PostPhotoBody
import com.dionis.becomingdev.model.photos.PostPhotoResponse
import javax.inject.Inject

class PostPhotoApi @Inject constructor(private val postPhotoEndPoint: PostPhotoEndPoint) {

    suspend fun postPhoto(body: PostPhotoBody): PostPhotoResponse = postPhotoEndPoint.postPhoto(body)
}