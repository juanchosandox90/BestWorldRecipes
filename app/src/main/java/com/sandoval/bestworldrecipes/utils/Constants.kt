package com.sandoval.bestworldrecipes.utils

class Constants {
    companion object {

        // [TYPE_YOUR_API_KEY_HERE] - REPLACE BEFORE UPLOAD TO GITHUB!!!
        const val API_KEY = "[TYPE_YOUR_API_KEY_HERE]"
        const val URL_BASE = "https://api.spoonacular.com"

        // API Queries keys
        const val QUERY_API_KEY = "apiKey"
        const val QUERY_NUMBER = "number"
        const val QUERY_TYPE = "type"
        const val QUERY_DIET = "diet"
        const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
        const val QUERY_FILL_INGREDIENTS = "fillIngredients"

        //ROOM Database
        const val RECIPES_DATABASE = "recipes_database"
        const val RECIPES_TABLE = "recipes_table"
    }
}