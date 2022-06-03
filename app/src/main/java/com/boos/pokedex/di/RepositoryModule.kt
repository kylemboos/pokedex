package com.boos.pokedex.di

import com.boos.pokedex.features.browse.BrowseRepository
import com.boos.pokedex.features.browse.BrowseRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindBrowseRepository(browseRepositoryImpl: BrowseRepositoryImpl): BrowseRepository
}