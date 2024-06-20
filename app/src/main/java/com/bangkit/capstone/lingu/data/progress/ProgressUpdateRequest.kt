package com.bangkit.capstone.lingu.data.progress

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ProgressUpdateRequest(

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("character")
	val character: String,

	@field:SerializedName("confidenceScore")
	val confidenceScore: Double

) : Parcelable
