package koreatlwls.mavericks_mvi_compose.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import koreatlwls.mavericks_mvi_compose.ui.theme.MavericksMVIComposeTheme

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = mavericksViewModel()
) {
    val uiState by viewModel.collectAsState()

    SearchScreen(
        keyword = uiState.keyword,
        uiState = uiState,
        onValueChange = {
            viewModel.fetchBookByKeyword(it)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    keyword: String,
    uiState: SearchUiState,
    onValueChange: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = keyword,
            onValueChange = onValueChange,
            label = {
                Text(text = "책")
            },
            placeholder = {
                Text(text = "검색어를 입력하세요.")
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                }
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (uiState.books) {
                is Loading -> {
                    CircularProgressIndicator()
                }

                is Success -> {
                    val books = uiState.books.invoke()
                    if (books.isEmpty()) {
                        Text("검색 결과가 없습니다.")
                    } else {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(books) {
                                Text(text = it.title)
                            }
                        }
                    }
                }

                is Fail -> {
                    Text(uiState.books.error.message ?: "네트워크를 확인하세요")
                }

                is Uninitialized -> {
                    Text("상단의 검색바를 통해 검색을 시작해보세요.")
                }
            }
        }
    }
}

@Preview
@Composable
private fun SearchScreenPreview() {
    MavericksMVIComposeTheme {
        SearchScreen(
            keyword = "",
            uiState = SearchUiState(),
            onValueChange = {},
        )
    }
}