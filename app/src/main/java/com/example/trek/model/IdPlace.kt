package com.example.trek.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class IdPlace(

	@field:SerializedName("server_timestamp")
	val serverTimestamp: String? = null,

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("data")
	val data:@RawValue Data? = null
) : Parcelable

@Parcelize
data class MediaItem(

	@field:SerializedName("original")
	val original: Original? = null,

	@field:SerializedName("suitability")
	val suitability: List<String?>? = null,

	@field:SerializedName("quadkey")
	val quadkey:@RawValue Any? = null,

	@field:SerializedName("attribution")
	val attribution: Attribution? = null,

	@field:SerializedName("url_template")
	val urlTemplate: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("location")
	val location:@RawValue Any? = null,

	@field:SerializedName("source")
	val source: Source? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("created_by")
	val createdBy: String? = null,

	@field:SerializedName("url")
	val url: String? = null
) : Parcelable

@Parcelize
data class Source(

	@field:SerializedName("provider")
	val provider: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("external_id")
	val externalId:@RawValue Any? = null
) : Parcelable


data class Data(

	@field:SerializedName("place")
	val place: Place? = null,

	@field:SerializedName("places")
	val places: List<PlacesItem?>? = null
)

@Parcelize
data class TagsItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("key")
	val key: String? = null
) : Parcelable

@Parcelize
data class Usage(

	@field:SerializedName("square")
	val square: String? = null,

	@field:SerializedName("video_preview")
	val videoPreview:@RawValue Any? = null,

	@field:SerializedName("portrait")
	val portrait: String? = null,

	@field:SerializedName("landscape")
	val landscape: String? = null
) : Parcelable

@Parcelize
data class Original(

	@field:SerializedName("size")
	val size:@RawValue Any? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("height")
	val height: Int? = null
) : Parcelable


@Parcelize
data class MainMedia(

	@field:SerializedName("usage")
	val usage: Usage? = null,

	@field:SerializedName("media")
	val media: List<MediaItem?>? = null
) : Parcelable

@Parcelize
data class Attribution(

	@field:SerializedName("title_url")
	val titleUrl: String? = null,

	@field:SerializedName("license")
	val license: String? = null,

	@field:SerializedName("other")
	val other:@RawValue Any? = null,

	@field:SerializedName("author_url")
	val authorUrl: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("license_url")
	val licenseUrl: String? = null
) : Parcelable

@Parcelize
data class ExternalIdsItem(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("language_id")
	val languageId:@RawValue Any? = null,

	@field:SerializedName("type")
	val type: String? = null
) : Parcelable

@Parcelize
data class ReferencesItem(

	@field:SerializedName("price")
	val price:@RawValue Any? = null,

	@field:SerializedName("supplier")
	val supplier: String? = null,

	@field:SerializedName("flags")
	val flags:@RawValue List<Any?>? = null,

	@field:SerializedName("currency")
	val currency:@RawValue Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("language_id")
	val languageId: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("priority")
	val priority: Int? = null,

	@field:SerializedName("url")
	val url: String? = null
) : Parcelable

@Parcelize
data class Description(

	@field:SerializedName("provider")
	val provider:@RawValue Any? = null,

	@field:SerializedName("link")
	val link:@RawValue Any? = null,

	@field:SerializedName("translation_provider")
	val translationProvider:@RawValue Any? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("is_translated")
	val isTranslated: Boolean? = null
) : Parcelable

@Parcelize
data class Place(

	@field:SerializedName("media_count")
	val mediaCount: Int? = null,

	@field:SerializedName("references")
	val references: List<ReferencesItem?>? = null,

	@field:SerializedName("rating")
	val rating: Double? = null,

	@field:SerializedName("rating_local")
	val ratingLocal: Double? = null,

	@field:SerializedName("perex")
	val perex: String? = null,

	@field:SerializedName("description")
	val description: Description? = null,

	@field:SerializedName("parent_ids")
	val parentIds: List<String?>? = null,

	@field:SerializedName("thumbnail_url")
	val thumbnailUrl: String? = null,

	@field:SerializedName("duration")
	val duration: Int? = null,

	@field:SerializedName("address_is_approximated")
	val addressIsApproximated: Boolean? = null,

	@field:SerializedName("customer_rating")
	val customerRating:@RawValue Any? = null,

	@field:SerializedName("is_deleted")
	val isDeleted: Boolean? = null,

	@field:SerializedName("star_rating")
	val starRating:@RawValue Any? = null,

	@field:SerializedName("main_media")
	val mainMedia: MainMedia? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("categories")
	val categories: List<String?>? = null,

	@field:SerializedName("satellite")
	val satellite:@RawValue Any? = null,

	@field:SerializedName("email")
	val email:@RawValue Any? = null,

	@field:SerializedName("area")
	val area: Int? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("bounding_box")
	val boundingBox: BoundingBox? = null,

	@field:SerializedName("level")
	val level: String? = null,

	@field:SerializedName("name_suffix")
	val nameSuffix: String? = null,

	@field:SerializedName("admission")
	val admission: String? = null,

	@field:SerializedName("external_ids")
	val externalIds: List<ExternalIdsItem?>? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("tags")
	val tags: List<TagsItem?>? = null,

	@field:SerializedName("opening_hours_raw")
	val openingHoursRaw: String? = null,

	@field:SerializedName("quadkey")
	val quadkey: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("marker")
	val marker: String? = null,

	@field:SerializedName("original_name")
	val originalName: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("opening_hours")
	val openingHours:@RawValue Any? = null,

	@field:SerializedName("collection_count")
	val collectionCount: Int? = null,

	@field:SerializedName("location")
	val location:@RawValue Location? = null,

	@field:SerializedName("star_rating_unofficial")
	val starRatingUnofficial:@RawValue Any? = null,

	@field:SerializedName("server_timestamp")
	val serverTimestamp: String? = null,

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("data")
	val data:@RawValue Data? = null
) : Parcelable
