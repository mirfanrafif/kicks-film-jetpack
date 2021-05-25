package com.mirfanrafif.kicksfilm.data.source.remote.responses

import com.google.gson.annotations.SerializedName

data class GenresItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)