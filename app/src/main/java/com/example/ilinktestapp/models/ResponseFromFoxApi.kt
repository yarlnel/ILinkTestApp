package com.example.ilinktestapp.models

import com.google.gson.annotations.SerializedName

data class FoxResponse(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("link")
	val link: String? = null
)
