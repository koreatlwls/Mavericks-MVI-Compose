package koreatlwls.mavericks_mvi_compose.data

import koreatlwls.mavericks_mvi_compose.domain.BookRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

class BookRepositoryImpl @Inject constructor(
    private val bookApiService: BookApiService
) : BookRepository {

    override suspend fun getBookByKeyword(keyword: String): List<Item> = suspendCancellableCoroutine { continuation ->
        val call = bookApiService.getBookByKeyword(keyword)
        call.enqueue(object : Callback<BookResponse> {
            override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { bookResponse ->
                        continuation.resume(bookResponse.items) {
                            call.cancel()
                        }
                    } ?: run {
                        continuation.resumeWithException(Exception("Empty response body"))
                    }
                } else {
                    continuation.resumeWithException(Exception(response.errorBody()?.string()))
                }
            }

            override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                continuation.resumeWithException(t)
            }
        })

        continuation.invokeOnCancellation {
            call.cancel()
        }
    }

}