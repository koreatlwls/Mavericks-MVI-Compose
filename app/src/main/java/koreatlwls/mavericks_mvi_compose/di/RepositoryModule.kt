package koreatlwls.mavericks_mvi_compose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import koreatlwls.mavericks_mvi_compose.data.BookRepositoryImpl
import koreatlwls.mavericks_mvi_compose.domain.BookRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideBookRepository(bookRepositoryImpl: BookRepositoryImpl): BookRepository

}