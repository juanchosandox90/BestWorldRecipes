package com.sandoval.bestworldrecipes.di

import android.content.Context
import androidx.room.Room
import com.sandoval.bestworldrecipes.data.database.RecipesDatabase
import com.sandoval.bestworldrecipes.utils.Constants.Companion.RECIPES_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        RecipesDatabase::class.java,
        RECIPES_DATABASE
    ).build()

    @Singleton
    @Provides
    fun provideDao(
        database: RecipesDatabase
    ) = database.recipesDao()
}