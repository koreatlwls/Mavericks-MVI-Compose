package koreatlwls.mavericks_mvi_compose.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookResponse(
    @Json(name = "display")
    val display: Int,
    @Json(name = "items")
    val items: List<Item>,
    @Json(name = "lastBuildDate")
    val lastBuildDate: String,
    @Json(name = "start")
    val start: Int,
    @Json(name = "total")
    val total: Int
)