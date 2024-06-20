package com.bangkit.capstone.lingu.data.progress

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ProgressUpdateResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
) : Parcelable

@Parcelize
data class Data(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("character")
	val character: String,

	@field:SerializedName("confidenceScore")
	val confidenceScore: Double,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("userId")
	val userId: String
) : Parcelable
