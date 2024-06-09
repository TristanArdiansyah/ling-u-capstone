package com.bangkit.capstone.lingu.data.pref

data class UserModel(
    val uid: String,
    val displayName: String,
    val email: String,
    val token: String,
    val isLogin: Boolean
)
