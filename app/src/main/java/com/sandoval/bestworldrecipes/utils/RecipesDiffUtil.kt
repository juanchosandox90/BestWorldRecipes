package com.sandoval.bestworldrecipes.utils

import androidx.recyclerview.widget.DiffUtil
import com.sandoval.bestworldrecipes.data.models.Result

class RecipesDiffUtil<T>(
    private val oldRecipes: List<T>,
    private val newRecipes: List<T>
) : DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return oldRecipes.size
    }

    override fun getNewListSize(): Int {
        return newRecipes.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldRecipes[oldItemPosition] === newRecipes[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldRecipes[oldItemPosition] == newRecipes[newItemPosition]
    }
}