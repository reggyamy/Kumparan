package com.reggya.kumparantechnicaltest.core.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbulsResponse(

	@field:SerializedName("AlbulsResponse")
	val albulsResponse: List<AlbulsResponseItem?>? = null
) : Parcelable

@Parcelize
data class AlbulsResponseItem(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
) : Parcelable
