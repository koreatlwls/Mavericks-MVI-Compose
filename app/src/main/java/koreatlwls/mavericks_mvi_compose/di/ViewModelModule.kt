package koreatlwls.mavericks_mvi_compose.di

import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.MavericksViewModelComponent
import com.airbnb.mvrx.hilt.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap
import koreatlwls.mavericks_mvi_compose.ui.SearchViewModel

@Module
@InstallIn(MavericksViewModelComponent::class)
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun searchViewModelFactory(factory: SearchViewModel.Factory): AssistedViewModelFactory<*, *>
}