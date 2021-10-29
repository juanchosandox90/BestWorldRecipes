package com.sandoval.bestworldrecipes.data.remote

import com.sandoval.bestworldrecipes.data.models.FoodJoke
import com.sandoval.bestworldrecipes.data.models.FoodRecipe
import com.sandoval.bestworldrecipes.data.network.FoodRecipesApi
import retrofit2.Response
import javax.inject.Inject

// This Data source will connect directly to spoonacular API
class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }

    suspend fun searchRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.searchRecipes(queries)
    }

    suspend fun getFoodjoke(apiKey: String): Response<FoodJoke> {
        return foodRecipesApi.getFoodJoke(apiKey)
    }
}