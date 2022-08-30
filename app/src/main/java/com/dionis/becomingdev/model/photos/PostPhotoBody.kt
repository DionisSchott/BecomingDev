package com.dionis.becomingdev.model.photos

import java.io.File

data class PostPhotoBody(
    val photo: File,
    val developer_id: Int
)
