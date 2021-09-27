package com.example.ilinktestapp.models

import com.google.gson.annotations.SerializedName

data class DuckResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("url")
	val imageUrl: String? = null
)
