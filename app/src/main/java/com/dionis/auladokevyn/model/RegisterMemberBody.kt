package com.dionis.auladokevyn.model

import android.provider.ContactsContract
import java.io.Serializable

data class RegisterMemberBody(
    val name: String,
    val lastname: String,
    val age: String,
    val technology: String,
    val experience: String,
    val socials: String,
    val email: String,
    val contact: String,

    ) : Serializable

//name: string,
//lastname - string
//age - integer
//technology - string
//experiencie - string
//socials - Link string
//email - email string
//contact - integer