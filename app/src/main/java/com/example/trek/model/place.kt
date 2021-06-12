package com.example.trek.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

//@Parcelize
//data class Data(
//
//	@field:SerializedName("places")
//	val places: List<PlacesItem?>? = null
//) : Parcelable

//@Parcelize
//data class Place(
//	@field:SerializedName("server_timestamp")
//	val serverTimestamp: String? = null,
//
//	@field:SerializedName("status_code")
//	val statusCode: Int? = null,
//
//	@field:SerializedName("data")
//	val data: Data? = null
//) : Parcelable

@Parcelize
data class PlacesItem(

	@field:SerializedName("bounding_box")
	val boundingBox: BoundingBox? = null,

	@field:SerializedName("level")
	val level: String? = null,

	@field:SerializedName("name_suffix")
	val nameSuffix: String? = null,

	@field:SerializedName("rating")
	val rating: Double? = null,

	@field:SerializedName("rating_local")
	val ratingLocal: Double? = null,

	@field:SerializedName("perex")
	val perex: String? = null,

	@field:SerializedName("parent_ids")
	val parentIds: List<String?>? = null,

	@field:SerializedName("thumbnail_url")
	val thumbnailUrl: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("duration")
	val duration: Int? = null,

	@field:SerializedName("customer_rating")
	val customerRating: Double? = null,

	@field:SerializedName("quadkey")
	val quadkey: String? = null,

	@field:SerializedName("marker")
	val marker: String? = null,

	@field:SerializedName("original_name")
	val originalName: String? = null,

	@field:SerializedName("star_rating")
	val starRating: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("location")
	val location: Loc? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("categories")
	val categories: List<String?>? = null,

	@field:SerializedName("star_rating_unofficial")
	val starRatingUnofficial: Double? = null,

	val viewType: Int? = 0,

	var isFav: Boolean? = false
) : Parcelable


@Parcelize
data class BoundingBox(

	@field:SerializedName("east")
	val east: Double? = null,

	@field:SerializedName("south")
	val south: Double? = null,

	@field:SerializedName("north")
	val north: Double? = null,

	@field:SerializedName("west")
	val west: Double? = null
) : Parcelable

@Parcelize
data class Loc(
		@field:SerializedName("lng")
		val lng: Double? = null,

		@field:SerializedName("lat")
		val lat: Double? = null,
) : Parcelable

