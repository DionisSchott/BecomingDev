package com.dionis.becomingdev.domain.model

data class MembersItem(
    val Photos: List<Photo>,
    val age: Int,
    val contact: String,
    val email: String,
    val experience: String,
    val id: Int,
    val lastname: String,
    val name: String,
    val socials: String,
    val technology: String
)