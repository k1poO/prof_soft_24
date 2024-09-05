package com.example.finalproject.domain.security

interface PasswordHasher {

    fun hash(password: String): String
}