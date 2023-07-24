package koreatlwls.mavericks_mvi_compose.domain

import koreatlwls.mavericks_mvi_compose.data.Item

interface BookRepository {

    suspend fun getBookByKeyword(keyword: String): List<Item>

}