package koreatlwls.mavericks_mvi_compose.ui

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import koreatlwls.mavericks_mvi_compose.data.Item
import koreatlwls.mavericks_mvi_compose.domain.BookRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

data class SearchUiState(
    val keyword: String = "",
    val books: Async<List<Item>> = Uninitialized
) : MavericksState

class SearchViewModel @AssistedInject constructor(
    @Assisted initialState: SearchUiState,
    private val bookRepository: BookRepository
) : MavericksViewModel<SearchUiState>(initialState) {

    private lateinit var searchJob: Job

    fun fetchBookByKeyword(keyword: String) {
        setState {
            copy(keyword = keyword)
        }

        if (::searchJob.isInitialized && searchJob.isActive) {
            searchJob.cancel()
        }

        searchJob = suspend {
            delay(500)
            bookRepository.getBookByKeyword(keyword)
        }.execute { copy(books = it) }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<SearchViewModel, SearchUiState> {
        override fun create(state: SearchUiState): SearchViewModel
    }

    companion object : MavericksViewModelFactory<SearchViewModel, SearchUiState> by hiltMavericksViewModelFactory()

}