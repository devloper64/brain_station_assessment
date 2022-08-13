package com.example.brain_station_assessment.data.dto.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class RepositoriesResponse(
    @Json(name = "incomplete_results")
    val incompleteResults: Boolean?,
    @Json(name = "items")
    val items: List<Item?>?,
    @Json(name = "total_count")
    val totalCount: Int?
): Serializable