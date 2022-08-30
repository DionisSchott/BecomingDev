package com.dionis.becomingdev.data.api.photo

import com.dionis.becomingdev.model.photos.PostPhotoBody
import com.dionis.becomingdev.model.photos.PostPhotoResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface PostPhotoEndPoint {

    @POST("photos")
    suspend fun postPhoto(@Body body: PostPhotoBody): PostPhotoResponse

}