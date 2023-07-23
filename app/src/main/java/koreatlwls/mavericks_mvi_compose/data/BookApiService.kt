package koreatlwls.mavericks_mvi_compose.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiService {

    @GET("/v1/search/book.json")
    fun getBookByKeyword(@Query("query") query: String): Call<BookResponse>

}