package com.bangkit.capstone.lingu.data.register

import com.bangkit.capstone.lingu.data.login.Data

data class RegisterResponse(
    val status: String,
    val message: String,
    val data: Data
)