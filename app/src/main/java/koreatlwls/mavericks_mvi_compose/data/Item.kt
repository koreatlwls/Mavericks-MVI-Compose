package koreatlwls.mavericks_mvi_compose.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item(
    @Json(name = "author")
    val author: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "discount")
    val discount: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "isbn")
    val isbn: String,
    @Json(name = "link")
    val link: String,
    @Json(name = "pubdate")
    val pubdate: String,
    @Json(name = "publisher")
    val publisher: String,
    @Json(name = "title")
    val title: String
)