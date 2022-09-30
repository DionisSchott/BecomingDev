package com.dionis.becomingdev.model.editMember

import java.io.Serializable

data class EditUserBody(

    val name: String,
    val lastname: String,
    val age: Int,
    val technology: String,
    val experience: String,
    val socials: String,
    val email: String,
    val contact: String,

    ) : Serializable

