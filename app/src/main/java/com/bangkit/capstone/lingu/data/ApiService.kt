package com.bangkit.capstone.lingu.data

import com.bangkit.capstone.lingu.data.login.LoginResponse
import com.bangkit.capstone.lingu.data.progress.ProgressResponse
import com.bangkit.capstone.lingu.data.progress.ProgressUpdateRequest
import com.bangkit.capstone.lingu.data.progress.ProgressUpdateResponse
import com.bangkit.capstone.lingu.data.register.RegisterResponse
import com.google.android.gms.common.api.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("fullName") fullName: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confirmPassword") confirmPassword: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("progress")
    suspend fun progress(
        @Header("Authorization") token: String,
    ): ProgressResponse

    @POST("predict")
    suspend fun predict(
        @Header("Authorization") token: String,
        @Body requestBody: ProgressUpdateRequest
    ): ProgressUpdateResponse
}

