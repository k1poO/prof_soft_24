package com.example.finalproject.data.security

import com.example.finalproject.domain.security.PasswordHasher
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Md5PasswordHasher @Inject constructor() : PasswordHasher {

    override fun hash(password: String): String {
        val digest = MessageDigest.getInstance("MD5")
        val hashBytes = digest.digest(password.toByteArray())
        return hashBytes.joinToString("") { "%02x".format(it) }
    }
}