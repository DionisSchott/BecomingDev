package com.dionis.becomingdev.model

data class RegisterMemberResponse(
    val id: Int,
    val name: String,
    val lastname: String,
    val age: Int,
    val technology: String,
    val socials: String,
    val email: String,
    val contact: String,
    val updated_at: String,
    val created_at: String

)