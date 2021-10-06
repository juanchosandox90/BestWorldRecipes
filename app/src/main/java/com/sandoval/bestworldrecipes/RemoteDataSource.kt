package com.sandoval.bestworldrecipes

import com.sandoval.bestworldrecipes.data.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

// This Data source will connect directly to spoonacular API
class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }
}