package com.example.cmp_b.core.session

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get

class SessionManager(private val settings: Settings) {

    companion object Keys {
        const val AUTH_TOKEN = "auth_token"
        const val REFRESH_TOKEN = "refresh_token"
        const val USER_MOBILE = "user_mobile"
        const val IS_LOGGED_IN = "is_logged_in"
    }

    var authToken: String?
        get() = settings.getStringOrNull(AUTH_TOKEN)
        set(value) = settings.putString(AUTH_TOKEN, value ?: "")

    var refreshToken: String?
        get() = settings.getStringOrNull(REFRESH_TOKEN)
        set(value) = settings.putString(REFRESH_TOKEN, value ?: "")

    var userMobile: String?
        get() = settings.getStringOrNull(USER_MOBILE)
        set(value) = settings.putString(USER_MOBILE, value ?: "")

    var isLoggedIn: Boolean
        get() = settings.getBoolean(IS_LOGGED_IN, false)
        set(value) = settings.putBoolean(IS_LOGGED_IN, value)

    fun clearSession() {
        settings.clear()
    }
}
