package com.reggya.kumparantechnicaltest.core.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostResponse(

	@field:SerializedName("PostResponse")
	val postResponse: List<PostResponseItem?>? = null

) : Parcelable

@Parcelize
data class PostResponseItem(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
) : Parcelable
