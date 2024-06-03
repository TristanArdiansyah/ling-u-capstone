package com.bangkit.capstone.lingu.data.pref

data class UserModel(
    val email: String,
    val token: String,
    val isLogin: Boolean = false
)