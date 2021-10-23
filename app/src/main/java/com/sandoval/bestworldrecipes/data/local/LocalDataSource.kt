package com.sandoval.bestworldrecipes.data.local

import com.sandoval.bestworldrecipes.data.database.dao.RecipesDao
import com.sandoval.bestworldrecipes.data.database.entity.FavoritesEntity
import com.sandoval.bestworldrecipes.data.database.entity.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {
    /* Recipes Fragment Methods*/
    fun readRecipes(): Flow<List<RecipesEntity>> {
        return recipesDao.readRecipes()
    }

    suspend fun insertRecipes(recipesEntity: RecipesEntity) {
        recipesDao.insertRecipes(recipesEntity)
    }

    /*Favorite Recipes Methods*/
    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>> {
        return recipesDao.readFavoriteRecipes()
    }

    suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity) {
        recipesDao.insertFavoriteRecipe(favoritesEntity)
    }

    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity) {
        recipesDao.deleteFavoriteRecipe(favoritesEntity)
    }

    suspend fun deleteFavoriteRecipes() {
        recipesDao.deleteAllFavoritesRecipes()
    }
}