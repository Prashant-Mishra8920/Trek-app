package com.example.trek.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Direction(

	@field:SerializedName("route")
	val route: Route? = null,

	@field:SerializedName("info")
	val info: Info? = null
) : Parcelable

@Parcelize
data class LegsItem(

	@field:SerializedName("hasTollRoad")
	val hasTollRoad: Boolean? = null,

	@field:SerializedName("hasBridge")
	val hasBridge: Boolean? = null,

	@field:SerializedName("destNarrative")
	val destNarrative: String? = null,

	@field:SerializedName("distance")
	val distance: Double? = null,

	@field:SerializedName("hasTimedRestriction")
	val hasTimedRestriction: Boolean? = null,

	@field:SerializedName("hasTunnel")
	val hasTunnel: Boolean? = null,

	@field:SerializedName("hasHighway")
	val hasHighway: Boolean? = null,

	@field:SerializedName("index")
	val index: Int? = null,

	@field:SerializedName("formattedTime")
	val formattedTime: String? = null,

	@field:SerializedName("origIndex")
	val origIndex: Int? = null,

	@field:SerializedName("hasAccessRestriction")
	val hasAccessRestriction: Boolean? = null,

	@field:SerializedName("hasSeasonalClosure")
	val hasSeasonalClosure: Boolean? = null,

	@field:SerializedName("hasCountryCross")
	val hasCountryCross: Boolean? = null,

	@field:SerializedName("roadGradeStrategy")
	val roadGradeStrategy: @RawValue List<Any>? = null,

	@field:SerializedName("destIndex")
	val destIndex: Int? = null,

	@field:SerializedName("time")
	val time: Int? = null,

	@field:SerializedName("hasUnpaved")
	val hasUnpaved: Boolean? = null,

	@field:SerializedName("origNarrative")
	val origNarrative: String? = null,

	@field:SerializedName("maneuvers")
	val maneuvers: List<ManeuversItem?>? = null,

	@field:SerializedName("hasFerry")
	val hasFerry: Boolean? = null
) : Parcelable

@Parcelize
data class Options(

	@field:SerializedName("arteryWeights")
	val arteryWeights: List<Double>? = null,

	@field:SerializedName("cyclingRoadFactor")
	val cyclingRoadFactor: Int? = null,

	@field:SerializedName("timeType")
	val timeType: Int? = null,

	@field:SerializedName("useTraffic")
	val useTraffic: Boolean? = null,

	@field:SerializedName("returnLinkDirections")
	val returnLinkDirections: Boolean? = null,

	@field:SerializedName("countryBoundaryDisplay")
	val countryBoundaryDisplay: Boolean? = null,

	@field:SerializedName("enhancedNarrative")
	val enhancedNarrative: Boolean? = null,

	@field:SerializedName("locale")
	val locale: String? = null,

	@field:SerializedName("tryAvoidLinkIds")
	val tryAvoidLinkIds: List<Int>? = null,

	@field:SerializedName("drivingStyle")
	val drivingStyle: Int? = null,

	@field:SerializedName("doReverseGeocode")
	val doReverseGeocode: Boolean? = null,

	@field:SerializedName("generalize")
	val generalize: Int? = null,

	@field:SerializedName("mustAvoidLinkIds")
	val mustAvoidLinkIds: List<Int>? = null,

	@field:SerializedName("sideOfStreetDisplay")
	val sideOfStreetDisplay: Boolean? = null,

	@field:SerializedName("routeType")
	val routeType: String? = null,

	@field:SerializedName("avoidTimedConditions")
	val avoidTimedConditions: Boolean? = null,

	@field:SerializedName("routeNumber")
	val routeNumber: Int? = null,

	@field:SerializedName("shapeFormat")
	val shapeFormat: String? = null,

	@field:SerializedName("maxWalkingDistance")
	val maxWalkingDistance: Int? = null,

	@field:SerializedName("destinationManeuverDisplay")
	val destinationManeuverDisplay: Boolean? = null,

	@field:SerializedName("transferPenalty")
	val transferPenalty: Int? = null,

	@field:SerializedName("narrativeType")
	val narrativeType: String? = null,

	@field:SerializedName("walkingSpeed")
	val walkingSpeed: Int? = null,

	@field:SerializedName("urbanAvoidFactor")
	val urbanAvoidFactor: Int? = null,

	@field:SerializedName("stateBoundaryDisplay")
	val stateBoundaryDisplay: Boolean? = null,

	@field:SerializedName("unit")
	val unit: String? = null,

	@field:SerializedName("highwayEfficiency")
	val highwayEfficiency: Int? = null,

	@field:SerializedName("maxLinkId")
	val maxLinkId: Int? = null,

	@field:SerializedName("maneuverPenalty")
	val maneuverPenalty: Int? = null,

	@field:SerializedName("avoidTripIds")
	val avoidTripIds: List<Int>? = null,

	@field:SerializedName("filterZoneFactor")
	val filterZoneFactor: Int? = null,

	@field:SerializedName("manmaps")
	val manmaps: String? = null
) : Parcelable

@Parcelize
data class Route(

	@field:SerializedName("hasTollRoad")
	val hasTollRoad: Boolean? = null,

	@field:SerializedName("hasBridge")
	val hasBridge: Boolean? = null,

	@field:SerializedName("boundingBox")
	val boundingBox: BoundingBox? = null,

	@field:SerializedName("distance")
	val distance: Double? = null,

	@field:SerializedName("hasTimedRestriction")
	val hasTimedRestriction: Boolean? = null,

	@field:SerializedName("hasTunnel")
	val hasTunnel: Boolean? = null,

	@field:SerializedName("hasHighway")
	val hasHighway: Boolean? = null,

	@field:SerializedName("computedWaypoints")
	val computedWaypoints: List<Int>? = null,

	@field:SerializedName("routeError")
	val routeError: RouteError? = null,

	@field:SerializedName("formattedTime")
	val formattedTime: String? = null,

	@field:SerializedName("sessionId")
	val sessionId: String? = null,

	@field:SerializedName("hasAccessRestriction")
	val hasAccessRestriction: Boolean? = null,

	@field:SerializedName("realTime")
	val realTime: Int? = null,

	@field:SerializedName("hasSeasonalClosure")
	val hasSeasonalClosure: Boolean? = null,

	@field:SerializedName("hasCountryCross")
	val hasCountryCross: Boolean? = null,

	@field:SerializedName("fuelUsed")
	val fuelUsed: Double? = null,

	@field:SerializedName("legs")
	val legs: List<LegsItem?>? = null,

	@field:SerializedName("options")
	val options: Options? = null,

	@field:SerializedName("locations")
	val locations: List<LocationsItem?>? = null,

	@field:SerializedName("time")
	val time: Int? = null,

	@field:SerializedName("hasUnpaved")
	val hasUnpaved: Boolean? = null,

	@field:SerializedName("locationSequence")
	val locationSequence: List<Int?>? = null,

	@field:SerializedName("hasFerry")
	val hasFerry: Boolean? = null
) : Parcelable

@Parcelize
data class Ul(

	@field:SerializedName("lng")
	val lng: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
) : Parcelable

@Parcelize
data class StartPoint(

	@field:SerializedName("lng")
	val lng: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
) : Parcelable

@Parcelize
data class LatLng(

	@field:SerializedName("lng")
	val lng: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
) : Parcelable

@Parcelize
data class RouteError(

	@field:SerializedName("errorCode")
	val errorCode: Int? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class DisplayLatLng(

	@field:SerializedName("lng")
	val lng: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
) : Parcelable

@Parcelize
data class Lr(

	@field:SerializedName("lng")
	val lng: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
) : Parcelable

@Parcelize
data class Info(

	@field:SerializedName("statuscode")
	val statuscode: Int? = null,

	@field:SerializedName("copyright")
	val copyright: Copyright? = null,

	@field:SerializedName("messages")
	val messages: List<String>? = null
) : Parcelable

@Parcelize
data class ManeuversItem(

	@field:SerializedName("distance")
	val distance: Double? = null,

	@field:SerializedName("streets")
	val streets: List<String?>? = null,

	@field:SerializedName("narrative")
	val narrative: String? = null,

	@field:SerializedName("turnType")
	val turnType: Int? = null,

	@field:SerializedName("startPoint")
	val startPoint: StartPoint? = null,

	@field:SerializedName("index")
	val index: Int? = null,

	@field:SerializedName("formattedTime")
	val formattedTime: String? = null,

	@field:SerializedName("directionName")
	val directionName: String? = null,

	@field:SerializedName("maneuverNotes")
	val maneuverNotes: List<String>? = null,

	@field:SerializedName("linkIds")
	val linkIds: List<Int>? = null,

	@field:SerializedName("signs")
	val signs: List<String>? = null,

	@field:SerializedName("transportMode")
	val transportMode: String? = null,

	@field:SerializedName("attributes")
	val attributes: Int? = null,

	@field:SerializedName("time")
	val time: Int? = null,

	@field:SerializedName("iconUrl")
	val iconUrl: String? = null,

	@field:SerializedName("direction")
	val direction: Int? = null,

	@field:SerializedName("mapUrl")
	val mapUrl: String? = null
) : Parcelable

@Parcelize
data class LocationsItem(

	@field:SerializedName("dragPoint")
	val dragPoint: Boolean? = null,

	@field:SerializedName("displayLatLng")
	val displayLatLng: DisplayLatLng? = null,

	@field:SerializedName("adminArea4")
	val adminArea4: String? = null,

	@field:SerializedName("adminArea5")
	val adminArea5: String? = null,

	@field:SerializedName("postalCode")
	val postalCode: String? = null,

	@field:SerializedName("adminArea1")
	val adminArea1: String? = null,

	@field:SerializedName("adminArea3")
	val adminArea3: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("sideOfStreet")
	val sideOfStreet: String? = null,

	@field:SerializedName("geocodeQualityCode")
	val geocodeQualityCode: String? = null,

	@field:SerializedName("adminArea4Type")
	val adminArea4Type: String? = null,

	@field:SerializedName("linkId")
	val linkId: Int? = null,

	@field:SerializedName("street")
	val street: String? = null,

	@field:SerializedName("adminArea5Type")
	val adminArea5Type: String? = null,

	@field:SerializedName("geocodeQuality")
	val geocodeQuality: String? = null,

	@field:SerializedName("adminArea1Type")
	val adminArea1Type: String? = null,

	@field:SerializedName("adminArea3Type")
	val adminArea3Type: String? = null,

	@field:SerializedName("latLng")
	val latLng: LatLng? = null
) : Parcelable

@Parcelize
data class Bounding(

	@field:SerializedName("lr")
	val lr: Lr? = null,

	@field:SerializedName("ul")
	val ul: Ul? = null
) : Parcelable

@Parcelize
data class Copyright(

	@field:SerializedName("imageAltText")
	val imageAltText: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("text")
	val text: String? = null
) : Parcelable
