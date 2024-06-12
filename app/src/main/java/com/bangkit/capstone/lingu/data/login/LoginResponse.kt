package com.bangkit.capstone.lingu.data.login

import com.bangkit.capstone.lingu.data.Data

data class LoginResponse(
    val status: String,
    val message: String,
    val data: Data
)
