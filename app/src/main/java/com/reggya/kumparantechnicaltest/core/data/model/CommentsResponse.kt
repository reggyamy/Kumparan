package com.reggya.kumparantechnicaltest.core.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommentsResponse(

	@field:SerializedName("CommentsResponse")
	val commentsResponse: List<CommentsResponseItem?>? = null
) : Parcelable

@Parcelize
data class CommentsResponseItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("postId")
	val postId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("email")
	val email: String? = null
) : Parcelable
