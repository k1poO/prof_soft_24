package com.example.finalproject.data.storage

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenProvider @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
) {
    fun getToken(): String {
        return sharedPreferencesManager.getAuthToken() ?: ""
    }

    fun saveToken(token: String) {
        sharedPreferencesManager.saveAuthToken(token)
    }

    fun clearToken() {
        sharedPreferencesManager.clearAuthToken()
    }

    fun isTokenSaved() : Boolean {
        return getToken().isNotEmpty()
    }
}

