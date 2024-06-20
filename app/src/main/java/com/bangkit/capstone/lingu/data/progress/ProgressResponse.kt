package com.bangkit.capstone.lingu.data.progress

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ProgressResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("status")
	val status: String
) : Parcelable

@Parcelize
data class CharactersItem(

	@field:SerializedName("character")
	val character: String,

	@field:SerializedName("confidenceScore")
	val confidenceScore: Double
) : Parcelable

@Parcelize
data class DataItem(

	@field:SerializedName("totalCharacters")
	val totalCharacters: Int,

	@field:SerializedName("percentCompleted")
	val percentCompleted: Double,

	@field:SerializedName("characters")
	val characters: List<CharactersItem?>? = null,

	@field:SerializedName("completedCharacters")
	val completedCharacters: Int,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("isComplete")
	val isComplete: Boolean
) : Parcelable
