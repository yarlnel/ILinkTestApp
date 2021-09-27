package com.example.ilinktestapp.models

import com.google.gson.annotations.SerializedName

data class CatResponse(
	@field:SerializedName("file")
	val file: String? = null
)
