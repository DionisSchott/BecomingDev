package com.dionis.becomingdev.data.storage

import android.content.Context

class SessionManager(context: Context) {

    private var sharedPreferences = context.getSharedPreferences(
        becomingDevSharedPreferences,
        Context.MODE_PRIVATE
    )

    companion object {
        private var becomingDevSharedPreferences = "BEAK_SHARED_PREFERENCES"
        private var TOKEN_KEY = "TOKEN_KEY"
        private var ID_KEY = "ID_KEY"

    }

    fun setToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString(TOKEN_KEY, token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN_KEY, "")
    }

    fun setId(id: Int) {
        sharedPreferences.edit().putInt(ID_KEY, id).apply()
    }

    fun getId(): Int {
        return sharedPreferences.getInt(ID_KEY, 0)
    }

}
